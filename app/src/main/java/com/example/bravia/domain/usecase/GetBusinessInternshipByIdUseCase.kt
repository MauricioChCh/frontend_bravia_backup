package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.repository.InternshipRepository
import javax.inject.Inject

class GetBusinessInternshipByIdUseCase @Inject constructor(
    private val repository: InternshipRepository
) {

    /**
     * Fetches a specific internship by its ID for a given business.
     *
     * @param businessId The ID of the business.
     * @param internshipId The ID of the internship to fetch.
     * @return A Result containing the Internship if found, or null if not found.
     */
    suspend operator fun invoke(username: String, internshipId: Long): Result<Internship?> {
        return repository.getBusinessInternshipById(username, internshipId)
    }
}