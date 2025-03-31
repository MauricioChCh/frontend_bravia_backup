package com.example.bravia.data.datasource.degree

import com.example.bravia.data.datasource.model.DegreeDTO

interface DegreeDataSource {
    fun getAllDegrees(): List<DegreeDTO>
}