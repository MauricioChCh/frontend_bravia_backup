package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.repository.InternshipRepository
import javax.inject.Inject

class GetBookmarkedInternshipsUseCase @Inject constructor (private val repository: InternshipRepository) {
    suspend operator fun invoke(userId: Long): Result<List<Internship>> {
        return repository.getBookmarkedInternships(userId)
    }
}