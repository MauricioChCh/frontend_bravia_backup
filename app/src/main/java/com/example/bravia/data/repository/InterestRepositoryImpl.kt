package com.example.bravia.data.repository

import com.example.bravia.data.mapper.InterestMapper
import com.example.bravia.data.remote.InterestRemoteDataSource
import com.example.bravia.domain.model.Interest
import com.example.bravia.domain.repository.InterestRepository
import java.net.UnknownHostException
import javax.inject.Inject


/**
 * Implementation of [InterestRepository] for accessing interest data.
 *
 * This class provides methods to retrieve a list of all interests and to get an interest by its ID.
 *
 * @property remoteDataSource The remote data source for fetching interest data.
 * @property mapper The mapper for converting between data and domain models.
 */
class InterestRepositoryImpl @Inject constructor(
    private val remoteDataSource: InterestRemoteDataSource,
    private val mapper: InterestMapper
) : InterestRepository {

    /**
     * Retrieves a list of all interests.
     *
     * @return A [Result] containing a list of [Interest] objects or an error.
     */
    override suspend fun getAllInterests(): Result<List<Interest>> {
        return try {
            remoteDataSource.getAllInterests().map {
                dto -> mapper.mapToDomainList(dto)
            }
        } catch (e: UnknownHostException) {
            throw Exception("Network error: Cannot connect to server. Please check your internet connection.")
        } catch (e: Exception) {
            throw Exception("Error fetching interests: ${e.message}")
        }
    }

    /**
     * Retrieves an interest by its ID.
     *
     * @param id The ID of the interest to retrieve.
     * @return A [Result] containing the [Interest] object or an error.
     */
    override suspend fun getInterestById(id: Long): Result<Interest?> {
        return try {
            val interestDTO = remoteDataSource.getInterestById(id)
            if (interestDTO.isSuccess) {
                val interest = interestDTO.getOrNull()
                Result.success(interest?.let { mapper.mapToDomain(it) })
            } else {
                Result.failure(Exception("Error fetching interest by ID: ${interestDTO.exceptionOrNull()?.message}"))
            }
        } catch (e: UnknownHostException) {
            throw Exception("Network error: Cannot connect to server. Please check your internet connection.")
        } catch (e: Exception) {
            throw Exception("Error fetching interest by ID: ${e.message}")
        }
    }
}