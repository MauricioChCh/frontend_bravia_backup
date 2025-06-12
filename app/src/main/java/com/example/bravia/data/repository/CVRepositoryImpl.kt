package com.example.bravia.data.repository

import com.example.bravia.data.remote.CVRemoteDataSource
import com.example.bravia.data.remote.dto.CVGenerationResponse
import com.example.bravia.data.remote.dto.CVRequestDTO
import com.example.bravia.domain.repository.CVRepository
import javax.inject.Inject

class CVRepositoryImpl @Inject constructor(
    private val remoteDataSource: CVRemoteDataSource
) : CVRepository {

    override suspend fun generateCV(request: CVRequestDTO): CVGenerationResponse {
        return remoteDataSource.generateCV(request)
    }
}