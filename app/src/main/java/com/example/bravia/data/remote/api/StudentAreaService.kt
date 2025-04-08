package com.example.bravia.data.remote.api

import com.example.bravia.data.remote.dto.InternshipDTO
import com.example.bravia.domain.model.BookmarkRequest

import java.util.List

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StudentAreaService {

    // This is the interface for the Student Page API
    // It show the interhips in the main page
    @GET("student/recommendedInternships")
    suspend fun getRecommendedInternships(): Response<List<InternshipDTO>>

    //It show a especific internship
    @GET("student/Internship/{internshipId}")
    suspend fun getInternshipById(@Path("id") id: Long): Response<InternshipDTO?>

    @GET("student/bookmarkedInternships")
    suspend fun getBookmarkedInternships(): Response<List<InternshipDTO>>

    @POST("student/bookmarkInternship")
    suspend fun bookmarkInternship(@Body bookmarkData: BookmarkRequest): Response<Unit>




}
