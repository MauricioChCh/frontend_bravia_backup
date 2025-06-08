package com.example.bravia.data.repository

import com.example.bravia.data.mapper.InternshipMapper
import com.example.bravia.data.remote.InternshipRemoteDataSource
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
                    mapper.mapToDomain(dto, bookmarkedInternships[dto.id] ?: false)
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
                    mapper.mapToDomain(dto, bookmarkedInternships[dto.id] ?: false)
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
    override suspend fun bookmarkInternship(id: Long, isBookmarked: Boolean) {
        bookmarkedInternships[id] = isBookmarked
    }

    /**
     * Retrieves all bookmarked internships.
     *
     * @return A [Result] containing a list of bookmarked [Internship] objects.
     */
    override suspend fun getBookmarkedInternships(): Result<List<Internship>> {
        return safeRepositoryCall {
            //TODO Esto deberia mas bien llamar a la API para obtener los internships con bookmark del usuario
            getRecommendedInternships().getOrThrow().filter { it.isMarked }
        }
    }

    override suspend fun getAllBusinessInternships(businessId: Long): Result<List<Internship>> {
        return safeRepositoryCall {
            val dtoResult = remoteDataSource.getAllBusinessInternships(businessId)

            // Desenvuelve el Result del DataSource
            if (dtoResult.isSuccess) {
                dtoResult.getOrNull()?.map { dto ->
                    mapper.mapToDomain(dto, bookmarkedInternships[dto.id] ?: false)
                } ?: emptyList()
            } else {
                throw dtoResult.exceptionOrNull() ?: Exception("Unknown error fetching internships")
            }
        }
    }

    override suspend fun getBusinessInternshipById(businessId: Long, internshipId: Long): Result<Internship?> {
        return safeRepositoryCall {
            val dtoResult = remoteDataSource.getBusinessInternshipById(businessId, internshipId)

            // Desenvuelve el Result del DataSource
            if (dtoResult.isSuccess) {
                dtoResult.getOrNull()?.let { dto ->
                    mapper.mapToDomain(dto, bookmarkedInternships[dto.id] ?: false)
                }
            } else {
                throw dtoResult.exceptionOrNull() ?: Exception("Unknown error fetching internship")
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

    override suspend fun newInternship(internship: NewInternship): Result<Internship?> {
        return safeRepositoryCall {
            val dto = mapper.mapToNewDTO(internship)
            val dtoResult = remoteDataSource.newInternship(dto)

            // Desenvuelve el Result del DataSource
            if (dtoResult.isSuccess) {
                dtoResult.getOrNull()?.let { mapper.mapToDomain(it, false) }
            } else {
                throw dtoResult.exceptionOrNull() ?: Exception("Unknown error creating internship")
            }
        }
    }

}