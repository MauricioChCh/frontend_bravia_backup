package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.InterestDTO
import com.example.bravia.domain.model.Interest

/**
 * Mapper class for converting between [InterestDTO] and [Interest] domain model.
 *
 * This class provides methods to map an InterestDTO to an Interest and vice versa.
 */
class InterestMapper {
    /**
     * Maps an InterestDTO to an Interest domain model.
     *
     * @param interestDTO The InterestDTO to be mapped.
     * @return The corresponding Interest domain model.
     */
    fun mapToDomain(interestDTO: InterestDTO): Interest {
        return Interest(
            id = interestDTO.id,
            name = interestDTO.name,
            category = interestDTO.category
        )
    }

    /**
     * Maps an Interest domain model to an InterestDTO.
     *
     * @param interest The Interest domain model to be mapped.
     * @return The corresponding InterestDTO.
     */
    fun mapToDto(interest: Interest): InterestDTO {
        return InterestDTO(
            id = interest.id,
            name = interest.name,
            category = interest.category
        )
    }
}