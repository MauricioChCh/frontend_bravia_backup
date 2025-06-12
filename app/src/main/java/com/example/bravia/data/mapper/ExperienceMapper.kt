package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.ExperienceDTO
import com.example.bravia.domain.model.Experience
import jakarta.inject.Inject

class ExperienceMapper  @Inject constructor() {
    // Mapper for Experience
    fun mapToDomain(dto: ExperienceDTO): Experience {
        return Experience(
            id = dto.id,
            name = dto.name,
            description = dto.description
        )
    }
}