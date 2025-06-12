package com.example.bravia.data.remote.api

import com.example.bravia.data.remote.dto.CompanyResponseDTO
import com.example.bravia.data.remote.dto.InternshipDTO
import com.example.bravia.data.remote.dto.StudentResponseDTO
import com.example.bravia.domain.model.BookmarkRequest

import java.util.List

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StudentService {

    // This is the interface for the Student Page API
    // It show the interhips in the main page
//    @GET("student/1/recommended-internships")
    @GET("internships")
    suspend fun getRecommendedInternships(): Response<List<InternshipDTO>>

    //It show a especific internship
    @GET("internships/{internshipId}")
    suspend fun getInternshipById( @Path("internshipId") id: Long): Response<InternshipDTO?>

    @GET("student/bookmarkedInternships")
    suspend fun getBookmarkedInternships(): Response<List<InternshipDTO>>

    @POST("student/bookmarkInternship")
    suspend fun bookmarkInternship(@Body bookmarkData: BookmarkRequest): Response<Unit>

    @GET("companies/{username}")
    suspend fun getStudentByUsername(@Path("username") username: String): Response<StudentResponseDTO?>



}
