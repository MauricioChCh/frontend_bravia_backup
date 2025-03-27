package com.example.bravia.data.repository

import com.example.bravia.data.datasource.InterestDataSource
import com.example.bravia.data.mapper.InterestMapper
import com.example.bravia.domain.model.Interest
import com.example.bravia.domain.repository.InterestRepository

class InterestRepositoryImpl (
    private val dataSource: InterestDataSource,
    private val mapper: InterestMapper
) : InterestRepository {

    override fun getAllInterests(): List<Interest> {
        return dataSource.getAllInterests().map { dto ->
            mapper.mapToDomain(dto)
        }
    }

    override fun getInterestById(id: Long): Interest? {
        return dataSource.getInterestById(id)?.let { mapper.mapToDomain(it) }
    }
}