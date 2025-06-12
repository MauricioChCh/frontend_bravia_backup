package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.CompanyTags
import com.example.bravia.domain.repository.CompanyRepository
import javax.inject.Inject

class UpdateCompanyTagsUseCase @Inject constructor(
    private val companyRepository: CompanyRepository
) {

    /**
     * Updates the tags of a company.
     *
     * @param companyId The ID of the company to update.
     * @param tags The list of tags to associate with the company.
     * @return A [Result] indicating success or failure of the operation.
     */
    suspend operator fun invoke(companyId: Long, companyTags: CompanyTags): Result<Unit> {
        return companyRepository.updateCompanyTags(companyId, companyTags)
    }
}