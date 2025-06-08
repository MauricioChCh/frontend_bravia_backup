package com.example.bravia.data.repository

import com.example.bravia.data.mapper.LocationMapper
import com.example.bravia.data.remote.LocationRemoteDataSource
import com.example.bravia.domain.model.Location
import com.example.bravia.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val remoteDataSource: LocationRemoteDataSource,
    private val mapper: LocationMapper
) : LocationRepository {

    /**
     * Fetches all business locations from the remote data source.
     *
     * @return A [Result] containing a list of [Location] objects or an error.
     */
    override suspend fun getAllBusinessLocations(companyId: Long): Result<List<Location>> {

        return try {
            val response = remoteDataSource.getAllBusinessLocations(companyId)
            if (response.isSuccess) {
                response.getOrNull()?.map { dto ->
                    mapper.mapToDomain(dto)
                }?.let { locations ->
                    Result.success(locations)
                } ?: Result.success(emptyList())
            } else {
                Result.failure(response.exceptionOrNull() ?: Exception("Unknown error fetching locations"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}