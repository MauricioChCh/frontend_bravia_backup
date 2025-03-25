package com.example.bravia.data.datasource

import com.example.bravia.data.datasource.model.InternshipDto

class InternshipDataSourceImpl : InternshipDataSource {
    override fun getAllInternships(): List<InternshipDto> {
        return InternshipProvider.internships
    }

    override fun getInternshipById(id: Long): InternshipDto? {
        return InternshipProvider.internships.find { it.id == id }
    }
}