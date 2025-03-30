package com.example.bravia.data.repository

import com.example.bravia.data.datasource.college.CollegeDataSource
import com.example.bravia.data.mapper.CollegeMapper
import com.example.bravia.domain.model.College
import com.example.bravia.domain.repository.CollegeRepository

class CollegeRepositoryImpl (
    private val dataSource: CollegeDataSource,
    private val mapper: CollegeMapper
) : CollegeRepository {

    override fun getAllColleges(): List<College> {
        return dataSource.getAllColleges().map { dto ->
            mapper.mapToDomain(dto)
        }
    }
}