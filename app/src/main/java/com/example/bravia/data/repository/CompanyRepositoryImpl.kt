package com.example.bravia.data.repository

import com.example.bravia.data.mapper.CompanyMapper
import com.example.bravia.data.mapper.TagMapper
import com.example.bravia.data.remote.CompanyRemoteDataSource
import com.example.bravia.domain.model.CompanName
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.CompanyBusinessAreas
import com.example.bravia.domain.model.CompanyDescription
import com.example.bravia.domain.model.CompanyTags
import com.example.bravia.domain.model.Location
import com.example.bravia.domain.model.Tag
import com.example.bravia.domain.repository.CompanyRepository
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val remoteDataSource: CompanyRemoteDataSource,
    private val companyMapper: CompanyMapper,
    private val tagMapper: TagMapper
) : CompanyRepository {

    /**
     * Retrieves a company by its ID.
     *
     * @param id The ID of the company to retrieve.
     * @return A [Result] containing the company data or an error.
     */
    override suspend fun getCompanyById(username: String): Result<Company> {
        return try {
            remoteDataSource.getCompanyById(username).map { dto ->
                companyMapper.mapToDomain(dto!!)
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error fetching company: ${e.message}"))
        }
    }

    override suspend fun getCompanyByCompanyId(id: Long): Result<Company?> {
        return try {
            remoteDataSource.getCompanyByCompanyId(id).map { dto ->
                companyMapper.mapToDomain(dto!!)
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error fetching company: ${e.message}"))
        }
    }

    override suspend fun getAllTags(): Result<List<Tag>> {
        return try {
            val response = remoteDataSource.getAllTags()
            if (response.isSuccess) {
                response.getOrNull()?.map { dto ->
                    tagMapper.mapToDomain(dto)
                }?.let { tags ->
                    Result.success(tags)
                } ?: Result.success(emptyList())
            } else {
                Result.failure(response.exceptionOrNull() ?: Exception("Unknown error fetching tags"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateCompanyDescription(company: CompanyDescription): Result<Unit> {
        return try {
            remoteDataSource.updateCompanyDescription(company)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Error updating company description: ${e.message}"))
        }
    }

    override suspend fun updateCompanyName(company: CompanName): Result<Unit> {
        return try {
            remoteDataSource.updateCompanyName(company)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Error updating company name: ${e.message}"))
        }
    }

    override suspend fun updateCompanyLocation(id: Long, location: Location): Result<Unit> {
        return try {
            remoteDataSource.updateCompanyLocation(id, location)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Error updating company location: ${e.message}"))
        }
    }

    override suspend fun updateCompanyTags(companyId: Long, companyTags: CompanyTags): Result<Unit> {
        return try {
            remoteDataSource.updateCompanyTags(companyId, companyTags)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Error updating company tags: ${e.message}"))
        }
    }

    override suspend fun updateCompanyBusinessAreas(companyId: Long, businessAreas: CompanyBusinessAreas): Result<Unit> {
        return try {
            remoteDataSource.updateCompanyBusinessAreas(companyId, businessAreas)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Error updating company business areas: ${e.message}"))
        }
    }


}