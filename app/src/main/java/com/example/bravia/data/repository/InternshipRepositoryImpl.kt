package com.example.bravia.data.repository

import com.example.bravia.data.datasource.intership.InternshipDataSource
import com.example.bravia.data.mapper.InternshipMapper
import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.repository.InternshipRepository

class InternshipRepositoryImpl(
    private val dataSource: InternshipDataSource,
    private val mapper: InternshipMapper
) : InternshipRepository {

    // Mapa para almacenar el estado de marcador de cada pasantía
    private val bookmarkedInternships = mutableMapOf<Long, Boolean>()

    override fun getAllInternships(): List<Internship> {
        return dataSource.getAllInternships().map { dto ->
            mapper.mapToDomain(dto, bookmarkedInternships[dto.id] ?: false)
        }
    }

    override fun getInternshipById(id: Long): Internship? {
        return dataSource.getInternshipById(id)?.let { dto ->
            mapper.mapToDomain(dto, bookmarkedInternships[dto.id] ?: false)
        }
    }

    override fun bookmarkInternship(id: Long, isBookmarked: Boolean) {
        bookmarkedInternships[id] = isBookmarked
    }

    override fun getBookmarkedInternships(): List<Internship> {
        return getAllInternships().filter { it.isBookmarked }
    }
}