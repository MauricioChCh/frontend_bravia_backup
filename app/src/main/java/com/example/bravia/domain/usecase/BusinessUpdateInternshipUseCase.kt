package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.model.UpdateInternship
import com.example.bravia.domain.repository.InternshipRepository
import javax.inject.Inject

class BusinessUpdateInternshipUseCase @Inject constructor(
  private val repository: InternshipRepository
) {
    /**
     * Updates an existing internship in the repository.
     *
     * @param internship The updated internship data.
     * @return A [Result] indicating success or failure.
     */
    suspend operator fun invoke(username: String, internship: UpdateInternship): Result<Internship?> {
        return repository.updateInternship(username, internship)
    }
}