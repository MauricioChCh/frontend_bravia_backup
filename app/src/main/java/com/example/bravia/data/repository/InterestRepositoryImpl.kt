package com.example.bravia.data.repository

import com.example.bravia.data.datasource.interest.InterestDataSource
import com.example.bravia.data.mapper.InterestMapper
import com.example.bravia.domain.model.Interest
import com.example.bravia.domain.repository.InterestRepository


/**
 * Implementation of the InterestRepository interface.
 *
 * This class provides methods to retrieve interests from the data source and map them to domain models.
 *
 * @property dataSource The data source for retrieving interests.
 * @property mapper The mapper for converting between DTOs and domain models.
 */
class InterestRepositoryImpl (
    private val dataSource: InterestDataSource,
    private val mapper: InterestMapper
) : InterestRepository {

    /**
     * Retrieves a list of all interests.
     *
     * @return A list of [Interest] representing all interests.
     */
    override fun getAllInterests(): List<Interest> {
        return dataSource.getAllInterests().map { dto ->
            mapper.mapToDomain(dto)
        }
    }

    override fun getInterestById(id: Long): Interest? {
        return dataSource.getInterestById(id)?.let { mapper.mapToDomain(it) }
    }
}