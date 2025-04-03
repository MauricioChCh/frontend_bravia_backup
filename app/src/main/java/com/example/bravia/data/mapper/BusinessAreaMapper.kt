package com.example.bravia.data.mapper

import com.example.bravia.domain.model.BusinessArea
import com.example.bravia.data.remote.dto.BusinessAreaDTO
import javax.inject.Inject

/**
 * Mapper class for converting between [BusinessAreaDTO] and [BusinessArea].
 *
 * This class provides methods to map data transfer objects (DTOs) to domain models and vice versa.
 */
class BusinessAreaMapper @Inject constructor() {

    fun mapToDomainList(dto: List<BusinessAreaDTO>): List<BusinessArea> {
        return dto.map { mapToDomain(it) }
    }

    /**
     * Maps a [BusinessAreaDTO] to a [BusinessArea].
     *
     * @param dto The [BusinessAreaDTO] to be mapped.
     * @return The corresponding [BusinessArea].
     */
    fun mapToDomain(dto: BusinessAreaDTO): BusinessArea {
        return BusinessArea(
            id = dto.id,
            name = dto.name
        )
    }

    /**
     * Maps a [BusinessArea] to a [BusinessAreaDTO].
     *
     * @param domain The [BusinessArea] to be mapped.
     * @return The corresponding [BusinessAreaDTO].
     */
    fun mapToDto(domain: BusinessArea): BusinessAreaDTO {
        return BusinessAreaDTO(
            id = domain.id,
            name = domain.name
        )
    }
}