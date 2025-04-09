package com.example.bravia.domain.repository

import com.example.bravia.domain.model.Internship

interface InternshipRepository {
    suspend fun getRecommendedInternships(): Result<List<Internship>>
    suspend fun getInternshipById(id: Long): Result<Internship?>
    suspend fun bookmarkInternship(id: Long, isBookmarked: Boolean)
    suspend fun getBookmarkedInternships(): Result<List<Internship>>
    suspend fun getAllBusinessInternships(id: Long): Result<List<Internship>>
    suspend fun getBusinessInternshipById(id: Long): Result<Internship?>
}