package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.InterestDTO
import com.example.bravia.domain.model.Interest
import javax.inject.Inject

/**
 * Mapper class for converting between [InterestDTO] and [Interest] domain model.
 *
 * This class provides methods to map an InterestDTO to an Interest and vice versa.
 */
class InterestMapper @Inject constructor() {

    fun mapToDomainList(dto: List<InterestDTO>): List<Interest> {
        return dto.map { mapToDomain(it) }
    }

    /**
     * Maps an InterestDTO to an Interest domain model.
     *
     * @param interestDTO The InterestDTO to be mapped.
     * @return The corresponding Interest domain model.
     */
    fun mapToDomain(interestDTO: InterestDTO): Interest {
        return Interest(
            id = interestDTO.id,
            name = interestDTO.name
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
            name = interest.name
        )
    }
}