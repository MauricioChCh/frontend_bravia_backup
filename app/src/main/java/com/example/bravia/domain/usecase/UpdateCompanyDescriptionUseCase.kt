package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.CompanyDescription
import com.example.bravia.domain.repository.CompanyRepository
import javax.inject.Inject

class UpdateCompanyDescriptionUseCase @Inject constructor(
    private val repository: CompanyRepository
) {
    /**
     * Updates the company description in the business profile.
     *
     * @param description The new description to be set for the company.
     * @return A [Result] indicating success or failure of the update operation.
     */
    suspend operator fun invoke(company: CompanyDescription): Result<Unit> {
        return repository.updateCompanyDescription(company)
    }
}