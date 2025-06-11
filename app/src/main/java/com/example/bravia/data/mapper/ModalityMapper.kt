package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.ModalityDTO
import com.example.bravia.domain.model.Modality
import javax.inject.Inject

class ModalityMapper @Inject constructor(

) {

    fun mapToDomain(dto: ModalityDTO?): Modality? {
        return dto?.let {
            Modality(
                id = it.id,
                name = it.name.toString(),
            )
        }
    }

    fun mapToDto(domain: Modality?): com.example.bravia.data.remote.dto.ModalityDTO {
        return ModalityDTO(
            id = domain?.id ?: 0,
            name = domain?.name ?: "",
        )
    }

}