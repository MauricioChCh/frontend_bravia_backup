package com.example.bravia.data.repository

import com.example.bravia.data.datasource.businessArea.BusinessAreaDataSource
import com.example.bravia.data.mapper.BusinessAreaMapper
import com.example.bravia.domain.model.BusinessArea
import com.example.bravia.domain.repository.BusinessAreaRepository



class BusinessAreaRepositoryImpl (
    private val dataSource: BusinessAreaDataSource,
    private val mapper: BusinessAreaMapper
) : BusinessAreaRepository {
    override fun getAllBusinessAreas(): List<BusinessArea> {
        return dataSource.getAllBusinessAreas().map { dto ->
            mapper.mapToDomain(dto)
        }
    }
}