package com.example.bravia.data.mapper

import com.example.bravia.data.datasource.model.DegreeDTO
import com.example.bravia.domain.model.Degree

class DegreeMapper {

    fun mapToDomain(dto: DegreeDTO): Degree {
        return Degree(
            id = dto.id,
            name = dto.name
        )
    }

    fun mapToDto(domain: Degree): DegreeDTO {
        return DegreeDTO(
            id = domain.id,
            name = domain.name
        )
    }
}