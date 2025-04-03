package com.example.bravia.data.mapper

import com.example.bravia.domain.model.College
import com.example.bravia.data.remote.dto.CollegeDTO
import javax.inject.Inject

/**
 * Mapper class for converting between [CollegeDTO] and [College].
 *
 * This class provides methods to map data transfer objects (DTOs) to domain models and vice versa.
 */
class CollegeMapper @Inject constructor() {



    fun mapToDomainList(dto: List<CollegeDTO>): List<College> {
        return dto.map { mapToDomain(it) }
    }

    /**
     * Maps a [CollegeDTO] to a [College].
     *
     * @param dto The [CollegeDTO] to be mapped.
     * @return The corresponding [College].
     */
    fun mapToDomain(dto: CollegeDTO): College {
        return College(
            id = dto.id,
            name = dto.name
        )
    }

    /**
     * Maps a [College] to a [CollegeDTO].
     *
     * @param domain The [College] to be mapped.
     * @return The corresponding [CollegeDTO].
     */
    fun mapToDto(domain: College): CollegeDTO {
        return CollegeDTO(
            id = domain.id,
            name = domain.name
        )
    }

}