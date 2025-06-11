package com.example.bravia.data.repository

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
    override suspend fun getAllInternshipsModalities(): Response<List<Modality>> {
        return try {
            remoteDataSource.getAllInternshipsModalities().map { dtoList ->
                dtoList.map { dto ->
                    mapper.mapToDomain(dto)
                }
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error fetching modalities: ${e.message}"))
        }
    }
}