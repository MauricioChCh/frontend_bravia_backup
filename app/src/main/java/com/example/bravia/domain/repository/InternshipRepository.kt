package com.example.bravia.domain.repository

import com.example.bravia.domain.model.Internship

interface InternshipRepository {
    fun getAllInternships(): List<Internship>
    fun getInternshipById(id: Long): Internship?
    fun bookmarkInternship(id: Long, isBookmarked: Boolean)
    fun getBookmarkedInternships(): List<Internship>
}