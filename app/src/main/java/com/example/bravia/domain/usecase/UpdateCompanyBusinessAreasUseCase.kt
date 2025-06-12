package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.CompanyBusinessAreas
import com.example.bravia.domain.repository.CompanyRepository
import javax.inject.Inject

class UpdateCompanyBusinessAreasUseCase @Inject constructor(
    private val companyRepository: CompanyRepository
) {

    /**
     * Updates the business areas of a company.
     *
     * @param companyId The ID of the company to update.
     * @param businessAreas The list of business areas to associate with the company.
     * @return A [Result] indicating success or failure of the operation.
     */
    suspend operator fun invoke(
        companyId: Long,
        businessAreas: CompanyBusinessAreas
    ): Result<Unit> {
        return companyRepository.updateCompanyBusinessAreas(companyId, businessAreas)
    }
}