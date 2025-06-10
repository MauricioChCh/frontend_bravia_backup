package com.example.bravia.data.remote.api

import com.example.bravia.data.remote.dto.CompanyResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface AdminService {

    @GET("admin/companies")
    suspend fun getAllCompanies(): Response<List<CompanyResponseDTO>>


}