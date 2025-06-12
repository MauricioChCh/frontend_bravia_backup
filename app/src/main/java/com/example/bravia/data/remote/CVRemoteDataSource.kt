package com.example.bravia.data.remote

import com.example.bravia.data.remote.api.CVService
import com.example.bravia.data.remote.dto.CVGenerationResponse
import com.example.bravia.data.remote.dto.CVRequestDTO
import javax.inject.Inject

class CVRemoteDataSource @Inject constructor(
    private val apiService: CVService
) {

    suspend fun generateCV(request: CVRequestDTO): CVGenerationResponse {
        val response = apiService.generateCV(request)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        } else {
            throw Exception("Error generating CV: ${response.message()}")
        }
    }
}