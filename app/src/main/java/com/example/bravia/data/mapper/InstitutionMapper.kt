package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.InstitutionDTO
import com.example.bravia.domain.model.Institution
import javax.inject.Inject

class InstitutionMapper @Inject constructor() {
    // Mapper for Institution
    fun mapToDomain(dto: InstitutionDTO): Institution {
        return Institution(
            id = dto.id,
            name = dto.name
        )
    }
}