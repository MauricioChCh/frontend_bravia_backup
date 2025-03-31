package com.example.bravia.data.repository

import com.example.bravia.data.datasource.degree.DegreeDataSource
import com.example.bravia.data.mapper.DegreeMapper
import com.example.bravia.domain.model.Degree
import com.example.bravia.domain.repository.DegreeRepository

class DegreeRepositoryImpl (
    private val degreeDataSource: DegreeDataSource,
    private val mapper: DegreeMapper
) : DegreeRepository {

    override fun getAllDegrees(): List<Degree> {
        return degreeDataSource.getAllDegrees().map { dto ->
            mapper.mapToDomain(dto)
        }
    }
}