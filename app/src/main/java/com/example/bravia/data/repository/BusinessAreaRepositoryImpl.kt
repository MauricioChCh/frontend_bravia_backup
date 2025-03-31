package com.example.bravia.data.repository

import com.example.bravia.data.datasource.businessArea.BusinessAreaDataSource
import com.example.bravia.data.mapper.BusinessAreaMapper
import com.example.bravia.domain.model.BusinessArea
import com.example.bravia.domain.repository.BusinessAreaRepository



/**
 * Implementation of [BusinessAreaRepository] for accessing business areas.
 *
 * This class provides methods to retrieve business areas from the data source and map them to domain models.
 *
 * @property dataSource The data source for accessing business areas.
 * @property mapper The mapper for converting between data transfer objects (DTOs) and domain models.
 */
class BusinessAreaRepositoryImpl (
    private val dataSource: BusinessAreaDataSource,
    private val mapper: BusinessAreaMapper
) : BusinessAreaRepository {
    /**
     * Retrieves a list of all business areas.
     *
     * @return A list of [BusinessArea] representing all business areas.
     */
    override fun getAllBusinessAreas(): List<BusinessArea> {
        return dataSource.getAllBusinessAreas().map { dto ->
            mapper.mapToDomain(dto)
        }
    }
}