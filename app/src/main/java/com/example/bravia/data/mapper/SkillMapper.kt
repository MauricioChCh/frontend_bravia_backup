package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.SkillDTO
import com.example.bravia.domain.model.Skill
import jakarta.inject.Inject

class SkillMapper @Inject constructor() {
    // Mapper for Skill
    fun mapToDomain(dto: SkillDTO): Skill {
        return Skill(
            id = dto.id,
            name = dto.name,
            description = dto.description
        )
    }
}