package com.example.bravia.data.remote.api

import com.example.bravia.data.remote.dto.CompanyResponseDTO
import com.example.bravia.data.remote.dto.StudentResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface AdminService {

    @GET("admin/companies")
    suspend fun getAllCompanies(): Response<List<CompanyResponseDTO>>

    @GET("admin/students")
    suspend fun getAllStudents(): Response<List<StudentResponseDTO>>
}