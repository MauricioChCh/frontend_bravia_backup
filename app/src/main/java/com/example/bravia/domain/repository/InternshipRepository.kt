package com.example.bravia.domain.repository

import com.example.bravia.data.remote.dto.InternshipDTO
import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.model.NewInternship
import com.example.bravia.domain.model.UpdateInternship

interface InternshipRepository {
    suspend fun getRecommendedInternships(username: String): Result<List<Internship>>
    suspend fun getInternshipById(id: Long): Result<Internship?>
    suspend fun bookmarkInternship(internshipId: Long, username: String, isBookmarked: Boolean) : Result<Unit>
    suspend fun getBookmarkedInternships(username: String): Result<List<Internship>>
    suspend fun getAllBusinessInternships(username: String): Result<List<Internship>>
    suspend fun getBusinessInternshipById(username: String, internshipId: Long): Result<Internship?>
    suspend fun newInternship(internship: NewInternship): Result<Internship?>
    suspend fun updateInternship(username: String, internship: UpdateInternship): Result<Internship?>
}