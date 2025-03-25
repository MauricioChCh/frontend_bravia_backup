package com.example.bravia.data.datasource

import com.example.bravia.data.datasource.model.InternshipDto

interface InternshipDataSource {
    fun getAllInternships(): List<InternshipDto>
    fun getInternshipById(id: Long): InternshipDto?
}