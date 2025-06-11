package com.example.bravia.domain.usecase

import com.example.bravia.domain.repository.InternshipRepository
import javax.inject.Inject

class BookmarkInternshipUseCase @Inject constructor (
    private val repository: InternshipRepository
) {
    suspend operator fun invoke(internshipId: Long, username: String, isBookmarked: Boolean) {
        repository.bookmarkInternship(internshipId, username, isBookmarked)
    }
}