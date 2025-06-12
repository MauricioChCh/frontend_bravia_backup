package com.example.bravia.domain.repository

import com.example.bravia.domain.model.CompanName
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.CompanyBusinessAreas
import com.example.bravia.domain.model.CompanyDescription
import com.example.bravia.domain.model.CompanyTags
import com.example.bravia.domain.model.Location
import com.example.bravia.domain.model.Tag

interface CompanyRepository {
    /**
     * Retrieves a company by its ID.
     *
     * @param id The ID of the company to retrieve.
     * @return A [Result] containing the company data or an error.
     */
    suspend fun getCompanyById(username: String): Result<Company?>

    suspend fun getCompanyByCompanyId(id: Long): Result<Company?>

    suspend fun getAllTags(): Result<List<Tag>>

    suspend fun updateCompanyDescription(company: CompanyDescription): Result<Unit>

    suspend fun updateCompanyName(company: CompanName): Result<Unit>

    suspend fun updateCompanyLocation(id: Long, location: Location): Result<Unit>

    suspend fun updateCompanyTags(companyId: Long, companyTags: CompanyTags): Result<Unit>

    suspend fun updateCompanyBusinessAreas(companyId: Long, businessAreas: CompanyBusinessAreas): Result<Unit>
}