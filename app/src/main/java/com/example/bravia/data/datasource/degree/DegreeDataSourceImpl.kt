package com.example.bravia.data.datasource.degree

import com.example.bravia.data.datasource.model.DegreeDTO

class DegreeDataSourceImpl : DegreeDataSource {
    override fun getAllDegrees(): List<DegreeDTO> {
        return DegreeProvider.degrees
    }
}