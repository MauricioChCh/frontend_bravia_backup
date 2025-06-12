package com.example.bravia.domain.repository

import com.example.bravia.data.remote.dto.CVGenerationResponse
import com.example.bravia.data.remote.dto.CVRequestDTO

interface CVRepository {
    suspend fun generateCV(request: CVRequestDTO): CVGenerationResponse
}