package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.DegreeDTO
import com.example.bravia.domain.model.Degree

/**
 * Mapper class for converting between [DegreeDTO] and [Degree] domain model.
 *
 * This class provides methods to map a DegreeDTO to a Degree and vice versa.
 */
class DegreeMapper {

    /**
     * Maps a DegreeDTO to a Degree domain model.
     *
     * @param dto The DegreeDTO to be mapped.
     * @return The corresponding Degree domain model.
     */
    fun mapToDomain(dto: DegreeDTO): Degree {
        return Degree(
            id = dto.id,
            name = dto.name
        )
    }

    /**
     * Maps a Degree domain model to a DegreeDTO.
     *
     * @param domain The Degree domain model to be mapped.
     * @return The corresponding DegreeDTO.
     */
    fun mapToDto(domain: Degree): DegreeDTO {
        return DegreeDTO(
            id = domain.id,
            name = domain.name
        )
    }
}