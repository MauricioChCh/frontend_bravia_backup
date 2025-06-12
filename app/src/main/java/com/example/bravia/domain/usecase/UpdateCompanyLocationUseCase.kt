package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Location
import com.example.bravia.domain.repository.CompanyRepository
import javax.inject.Inject

class UpdateCompanyLocationUseCase @Inject constructor(
    private val repository: CompanyRepository
) {
    /**
     * Updates the company location.
     *
     * @param company The company with updated location information.
     * @return A [Result] indicating success or failure of the update operation.
     */
    suspend operator fun invoke(id : Long, location: Location): Result<Unit> {
        return repository.updateCompanyLocation(id, location)
    }
}