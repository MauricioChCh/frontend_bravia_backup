package com.example.bravia.data.mapper

import com.example.bravia.data.datasource.model.InterestDTO
import com.example.bravia.domain.model.Interest

class InterestMapper {
    fun mapToDomain(interestDTO: InterestDTO): Interest {
        return Interest(
            id = interestDTO.id,
            name = interestDTO.name,
            category = interestDTO.category
        )
    }

    fun mapToDto(interest: Interest): InterestDTO {
        return InterestDTO(
            id = interest.id,
            name = interest.name,
            category = interest.category
        )
    }
}