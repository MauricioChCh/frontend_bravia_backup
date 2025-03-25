package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.repository.InternshipRepository

class GetBookmarkedInternshipsUseCase(private val repository: InternshipRepository) {
    operator fun invoke(): List<Internship> {
        return repository.getBookmarkedInternships()
    }
}