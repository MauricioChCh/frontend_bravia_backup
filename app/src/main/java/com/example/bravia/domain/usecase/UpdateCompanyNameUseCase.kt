package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.CompanName
import com.example.bravia.domain.repository.CompanyRepository
import javax.inject.Inject

class UpdateCompanyNameUseCase @Inject constructor(
    private val repository: CompanyRepository
) {

    /**
     * Updates the name of a company.
     *
     * @param companyId The ID of the company to update.
     * @param newName The new name for the company.
     * @return A [Result] indicating success or failure of the operation.
     */
    suspend operator fun invoke(company: CompanName): Result<Unit> {
        return repository.updateCompanyName(company)
    }

}