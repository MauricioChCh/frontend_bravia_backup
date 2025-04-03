package com.example.bravia.data.repository

import com.example.bravia.data.mapper.BusinessAreaMapper
import com.example.bravia.data.remote.BusinessAreaRemoteDataSource
import com.example.bravia.domain.model.BusinessArea
import com.example.bravia.domain.repository.BusinessAreaRepository
import java.net.UnknownHostException
import javax.inject.Inject


/**
 * Implementation of [BusinessAreaRepository] for accessing business areas.
 *
 * This class provides methods to retrieve business areas from the data source and map them to domain models.
 *
 * @property remoteDataSource The data source for accessing business areas.
 * @property mapper The mapper for converting between data transfer objects (DTOs) and domain models.
 */
class BusinessAreaRepositoryImpl @Inject constructor(
    private val remoteDataSource: BusinessAreaRemoteDataSource,
    private val mapper: BusinessAreaMapper
) : BusinessAreaRepository {
    /**
     * Retrieves a list of all business areas.
     *
     * @return A [Result] containing a list of [BusinessArea] objects or an error.
     */
    override suspend fun getAllBusinessAreas(): Result<List<BusinessArea>> {
        return try {
            remoteDataSource.getAllBusinessAreas().map { dto ->
                mapper.mapToDomainList(dto)
            }
        } catch (e: UnknownHostException){
            Result.failure(Exception("Network error: Cannot connect to server. Please check your internet connection."))
        } catch (e: Exception) {
            Result.failure(Exception("Error fetching business areas: ${e.message}"))
        }
    }
}