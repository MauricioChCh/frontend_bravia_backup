package com.example.bravia.data.mapper

import com.example.bravia.domain.model.BusinessArea
import com.example.bravia.data.datasource.model.BusinessAreaDTO


class BusinessAreaMapper {
    fun mapToDomain(dto: BusinessAreaDTO): BusinessArea {
        return BusinessArea(
            id = dto.id,
            name = dto.name
        )
    }

    fun mapToDto(domain: BusinessArea): BusinessAreaDTO {
        return BusinessAreaDTO(
            id = domain.id,
            name = domain.name
        )
    }
}