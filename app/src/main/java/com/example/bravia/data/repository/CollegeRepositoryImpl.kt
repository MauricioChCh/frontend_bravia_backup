package com.example.bravia.data.repository

import com.example.bravia.data.mapper.CollegeMapper
import com.example.bravia.data.remote.CollegeRemoteDataSource
import com.example.bravia.domain.model.College
import com.example.bravia.domain.repository.CollegeRepository
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Implementation of [CollegeRepository] for accessing college data.
 *
 * This class provides a method to retrieve a list of all colleges.
 */
class CollegeRepositoryImpl @Inject constructor(
    private val remoteDataSource: CollegeRemoteDataSource,
    private val mapper: CollegeMapper
) : CollegeRepository {

    /**
     * Retrieves a list of all colleges.
     *
     * @return A list of [College] representing all colleges.
     */
    override suspend fun getAllColleges(): Result<List<College>> {
        return try {
            remoteDataSource.getAllColleges().map { dto ->
                mapper.mapToDomainList(dto)
            }
        } catch (e: UnknownHostException) {
            Result.failure(Exception("Network error: Cannot connect to server. Please check your internet connection."))
        } catch (e: Exception) {
            Result.failure(Exception("Error fetching colleges: ${e.message}"))
        }
    }
}