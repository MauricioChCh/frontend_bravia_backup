package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.model.NewInternship
import com.example.bravia.domain.repository.InternshipRepository
import javax.inject.Inject

class BusinessNewInternshipUseCase @Inject constructor(
    private val repository: InternshipRepository
) {
    /**
     * Sends a new internship to the repository.
     *
     * @param internship The internship to be sent.
     * @return A [Result] indicating success or failure.
     */
    suspend operator fun invoke(internship: NewInternship): Result<Internship?> {
        return repository.newInternship(internship)
    }
}