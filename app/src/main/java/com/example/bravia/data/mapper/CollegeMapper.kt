package com.example.bravia.data.mapper

import com.example.bravia.domain.model.College
import com.example.bravia.data.datasource.model.CollegeDTO

class CollegeMapper {

    fun mapToDomain(dto: CollegeDTO): College {
        return College(
            id = dto.id,
            name = dto.name
        )
    }

    fun mapToDto(domain: College): CollegeDTO {
        return CollegeDTO(
            id = domain.id,
            name = domain.name
        )
    }

}