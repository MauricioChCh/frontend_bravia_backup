package com.example.bravia.data.repository

import com.example.bravia.data.mapper.DegreeMapper
import com.example.bravia.data.remote.DegreeRemoteDataSource
import com.example.bravia.domain.model.Degree
import com.example.bravia.domain.repository.DegreeRepository
import java.net.UnknownHostException
import javax.inject.Inject


/**
 * Implementation of [DegreeRepository] for accessing degree data.
 *
 * This class provides a method to retrieve a list of all degrees.
 */
class DegreeRepositoryImpl @Inject constructor(
    private val remoteDataSource: DegreeRemoteDataSource,
    private val mapper: DegreeMapper
) : DegreeRepository {

    /**
     * Retrieves a list of all degrees.
     *
     * @return A list of [Degree] representing all degrees.
     */
    override suspend fun getAllDegrees(): Result<List<Degree>> {
        return try {
            remoteDataSource.getAllDegrees().map { dto ->
                mapper.mapToDomainList(dto)
            }
        }catch (e: UnknownHostException) {
            throw Exception("Network error: Cannot connect to server. Please check your internet connection.")
        } catch (e: Exception) {
            throw Exception("Error fetching degrees: ${e.message}")
        }
    }
}