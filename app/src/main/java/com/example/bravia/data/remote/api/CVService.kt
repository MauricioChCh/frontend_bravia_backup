package com.example.bravia.data.remote.api


import com.example.bravia.data.remote.dto.CVGenerationResponse
import com.example.bravia.data.remote.dto.CVRequestDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CVService {
    @POST("cv/generate")
    suspend fun generateCV(@Body request: CVRequestDTO): Response<CVGenerationResponse>
}