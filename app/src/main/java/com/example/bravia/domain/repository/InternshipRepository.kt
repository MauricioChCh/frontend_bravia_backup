package com.example.bravia.domain.repository

import com.example.bravia.data.remote.dto.InternshipDTO
import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.model.NewInternship

interface InternshipRepository {
    suspend fun getRecommendedInternships(): Result<List<Internship>>
    suspend fun getInternshipById(id: Long): Result<Internship?>
    suspend fun bookmarkInternship(internshipId: Long, username: String, isBookmarked: Boolean) : Result<Unit>
    suspend fun getBookmarkedInternships(username: String): Result<List<Internship>>
    suspend fun getAllBusinessInternships(username: String): Result<List<Internship>>
    suspend fun getBusinessInternshipById(businessId: Long, internshipId: Long): Result<Internship?>
    suspend fun newInternship(internship: NewInternship): Result<Internship?>
}