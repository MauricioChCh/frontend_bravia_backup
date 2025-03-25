package com.example.bravia.domain.usecase

import com.example.bravia.domain.repository.InternshipRepository

class BookmarkInternshipUseCase(private val repository: InternshipRepository) {
    operator fun invoke(id: Long, isBookmarked: Boolean) {
        repository.bookmarkInternship(id, isBookmarked)
    }
}