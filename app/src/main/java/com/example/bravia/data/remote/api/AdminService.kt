package com.example.bravia.data.remote.api

import com.example.bravia.data.remote.dto.CompanyResponseDTO
import com.example.bravia.data.remote.dto.StudentResponseDTO
import com.example.bravia.data.remote.dto.UserReportResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AdminService {

    @GET("admin/companies")
    suspend fun getAllCompanies(): Response<List<CompanyResponseDTO>>

    @GET("admin/students")
    suspend fun getAllStudents(): Response<List<StudentResponseDTO>>

    @GET("userReports")
    suspend fun getAllUserReports(): Response<List<UserReportResponseDTO>>

    @GET("userReports/{reportId}")
    suspend fun getUserReportById(
        @Path("reportId") reportId: Long
    ): Response<UserReportResponseDTO>

}