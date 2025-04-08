package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.repository.InternshipRepository
import javax.inject.Inject

class GetRecommendedInternshipsUseCase @Inject constructor (private val repository: InternshipRepository) {
    operator suspend fun invoke(): Result<List<Internship>> {
        return repository.getRecommendedInternships()
    }
}