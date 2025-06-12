package com.example.bravia.data.repository

import com.example.bravia.data.mapper.CompanyMapper
import com.example.bravia.data.mapper.TagMapper
import com.example.bravia.data.remote.CompanyRemoteDataSource
import com.example.bravia.domain.model.Company
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
}