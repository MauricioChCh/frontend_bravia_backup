package com.example.bravia.data.repository

import com.example.bravia.data.datasource.college.CollegeDataSource
import com.example.bravia.data.mapper.CollegeMapper
import com.example.bravia.domain.model.College
import com.example.bravia.domain.repository.CollegeRepository

/**
 * Implementation of [CollegeRepository] for accessing college data.
 *
 * This class provides a method to retrieve a list of all colleges.
 */
class CollegeRepositoryImpl (
    private val dataSource: CollegeDataSource,
    private val mapper: CollegeMapper
) : CollegeRepository {

    /**
     * Retrieves a list of all colleges.
     *
     * @return A list of [College] representing all colleges.
     */
    override fun getAllColleges(): List<College> {
        return dataSource.getAllColleges().map { dto ->
            mapper.mapToDomain(dto)
        }
    }
}