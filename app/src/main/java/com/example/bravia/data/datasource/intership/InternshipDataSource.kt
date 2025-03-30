package com.example.bravia.data.datasource.intership

import com.example.bravia.data.datasource.model.InternshipDto

interface InternshipDataSource {
    fun getAllInternships(): List<InternshipDto>
    fun getInternshipById(id: Long): InternshipDto?
}