package com.example.bravia.data.repository

import com.example.bravia.data.mapper.CompanyMapper
import com.example.bravia.data.remote.CompanyRemoteDataSource
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.repository.CompanyRepository
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val remoteDataSource: CompanyRemoteDataSource,
    private val mapper: CompanyMapper
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
                mapper.mapToDomain(dto!!)
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error fetching company: ${e.message}"))
        }
    }

}