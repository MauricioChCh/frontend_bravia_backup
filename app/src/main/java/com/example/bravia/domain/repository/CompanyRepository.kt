package com.example.bravia.domain.repository

import com.example.bravia.domain.model.Company

interface CompanyRepository {
    /**
     * Retrieves a company by its ID.
     *
     * @param id The ID of the company to retrieve.
     * @return A [Result] containing the company data or an error.
     */
    suspend fun getCompanyById(username: String): Result<Company?>
}