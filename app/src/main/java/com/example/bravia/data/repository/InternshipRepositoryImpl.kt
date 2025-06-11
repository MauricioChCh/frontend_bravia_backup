package com.example.bravia.data.repository

import com.example.bravia.data.mapper.InternshipMapper
import com.example.bravia.data.remote.InternshipRemoteDataSource
import com.example.bravia.data.remote.dto.InternshipDTO
import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.model.NewInternship
import com.example.bravia.domain.repository.InternshipRepository
import java.net.UnknownHostException
import javax.inject.Inject

class InternshipRepositoryImpl @Inject constructor(
    private val remoteDataSource: InternshipRemoteDataSource,
    private val mapper: InternshipMapper
) : InternshipRepository {

    // Mapa para almacenar el estado de marcador de cada pasantía
    private val bookmarkedInternships = mutableMapOf<Long, Boolean>()

    /**
     * Retrieves a list of all internships.
     *
     * @return A [Result] containing a list of [Internship] objects or an error.
     */
    override suspend fun getRecommendedInternships(): Result<List<Internship>> {
        return safeRepositoryCall {
            val dtoResult = remoteDataSource.getRecommendedInternships()

            // Desenvuelve el Result del DataSource
            if (dtoResult.isSuccess) {
                dtoResult.getOrNull()?.map { dto ->
                    mapper.mapToDomain(dto)
                } ?: emptyList()
            } else {
                throw dtoResult.exceptionOrNull() ?: Exception("Unknown error fetching internships")
            }
        }
    }
    /**
     * Retrieves an internship by its ID.
     *
     * @param id The ID of the internship to retrieve.
     * @return A [Result] containing the [Internship] object or an error.
     */
    override suspend fun getInternshipById(id: Long): Result<Internship?> {
        return safeRepositoryCall {
            val dtoResult = remoteDataSource.getInternshipById(id)

            // Desenvuelve el Result del DataSource
            if (dtoResult.isSuccess) {
                dtoResult.getOrNull()?.let { dto ->
                    mapper.mapToDomain(dto)
                }
            } else {
                throw dtoResult.exceptionOrNull() ?: Exception("Unknown error fetching internship")
            }
        }
    }

    /**
     * Bookmarks or unbookmarks an internship.
     *
     * @param id The ID of the internship to bookmark.
     * @param isBookmarked Whether the internship should be bookmarked.
     */
    override suspend fun bookmarkInternship(internshipId: Long, username: String, isBookmarked: Boolean) : Result<Unit> {
        return safeRepositoryCall {
            // Llama al DataSource para realizar la operación de marcador
            remoteDataSource.bookmarkInternship(internshipId, username, isBookmarked)
        }
    }

    /**
     * Retrieves all bookmarked internships.
     *
     * @return A [Result] containing a list of bookmarked [Internship] objects.
     */
    override suspend fun getBookmarkedInternships(username: String): Result<List<Internship>> {
        return safeRepositoryCall {
            val dtoResult = remoteDataSource.getBookmarkedInternships(username)

            if( dtoResult.isSuccess) {
                dtoResult.getOrNull()?.map { dto ->
                    mapper.mapToDomain(dto)
                } ?: emptyList()
            } else {
                throw dtoResult.exceptionOrNull() ?: Exception("Unknown error fetching bookmarked internships")
            }
        }
    }

    override suspend fun getAllBusinessInternships(username: String): Result<List<Internship>> {
        return safeRepositoryCall {
            val dtoResult = remoteDataSource.getAllBusinessInternships(username)

            if (dtoResult.isSuccess) {
                // ✅ Aquí SÍ se devuelve la lista
                dtoResult.getOrNull()?.map { dto ->
                    mapper.mapToDomain(dto)
                } ?: emptyList()
            } else {
                throw dtoResult.exceptionOrNull() ?: Exception("Unknown error fetching internships")
            }
        }
    }


    override suspend fun getBusinessInternshipById(username: String, internshipId: Long): Result<Internship?> {
        return safeRepositoryCall {
            val dtoResult = remoteDataSource.getBusinessInternshipById(username, internshipId)

            // Desenvuelve el Result del DataSource
            if (dtoResult.isSuccess) {
                dtoResult.getOrNull()?.let { dto ->
                    mapper.mapToDomain(dto)
                }
            } else {
                throw dtoResult.exceptionOrNull() ?: Exception("Unknown error fetching internship")
            }
        }
    }

    override suspend fun newInternship(internship: NewInternship): Result<Internship?> {
        return safeRepositoryCall {
            val dto = mapper.mapToNewDTO(internship)
            val dtoResult = remoteDataSource.newInternship(dto)

            // Desenvuelve el Result del DataSource
            if (dtoResult.isSuccess) {
                dtoResult.getOrNull()?.let { mapper.mapToDomain(it) }
            } else {
                throw dtoResult.exceptionOrNull() ?: Exception("Unknown error creating internship")
            }
        }
    }




    /**
     * Helper function to standardize error handling in repository methods.
     *
     * @param block The suspending function to execute.
     * @return [Result] containing the data if successful, or an exception if failed.
     */
    private inline fun <T> safeRepositoryCall(block: () -> T): Result<T> {
        return try {
            Result.success(block())
        } catch (e: UnknownHostException) {
            Result.failure(Exception("Network error: Cannot connect to server. Please check your internet connection."))
        } catch (e: Exception) {
            Result.failure(Exception("Error in repository operation: ${e.message}"))
        }
    }



}