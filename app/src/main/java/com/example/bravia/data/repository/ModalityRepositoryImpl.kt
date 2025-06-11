package com.example.bravia.data.repository

import com.example.bravia.data.mapper.ModalityMapper
import com.example.bravia.data.remote.ModalityRemoteDataSource
import com.example.bravia.domain.model.Modality
import com.example.bravia.domain.repository.ModalityRepository
import javax.inject.Inject

class ModalityRepositoryImpl @Inject constructor(
    private val remoteDataSource: ModalityRemoteDataSource,
    private val mapper: ModalityMapper
) : ModalityRepository {

    /**
     * Retrieves all internships modalities.
     *
     * @return A [Result] containing a list of modalities or an error.
     */
    override suspend fun getAllInternshipsModalities(): Result<List<Modality>> {
        return try {
            val response = remoteDataSource.getAllInternshipsModalities()
            if (response.isSuccess) {
                response.getOrNull()?.map { dto ->
                    mapper.mapToDomain(dto)
                }?.let { modalities ->
                    Result.success(modalities)
                } ?: Result.success(emptyList())
            } else {
                Result.failure(response.exceptionOrNull() ?: Exception("Unknown error fetching modalities"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error fetching modalities: ${e.message}"))
        } as Result<List<Modality>>
    }
}