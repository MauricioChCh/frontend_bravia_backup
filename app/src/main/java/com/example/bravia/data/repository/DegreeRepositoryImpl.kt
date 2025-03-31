package com.example.bravia.data.repository

import com.example.bravia.data.datasource.degree.DegreeDataSource
import com.example.bravia.data.mapper.DegreeMapper
import com.example.bravia.domain.model.Degree
import com.example.bravia.domain.repository.DegreeRepository


/**
 * Implementation of [DegreeRepository] for accessing degree data.
 *
 * This class provides a method to retrieve a list of all degrees.
 */
class DegreeRepositoryImpl (
    private val degreeDataSource: DegreeDataSource,
    private val mapper: DegreeMapper
) : DegreeRepository {

    /**
     * Retrieves a list of all degrees.
     *
     * @return A list of [Degree] representing all degrees.
     */
    override fun getAllDegrees(): List<Degree> {
        return degreeDataSource.getAllDegrees().map { dto ->
            mapper.mapToDomain(dto)
        }
    }
}