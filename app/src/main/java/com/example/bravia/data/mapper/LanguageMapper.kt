package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.LanguageDTO
import com.example.bravia.domain.model.Language
import javax.inject.Inject

class LanguageMapper @Inject constructor() {
    // Mapper for Language
    fun mapToDomain(dto: LanguageDTO): Language {
        return Language(
            id = dto.id,
            name = dto.name,
            level = dto.level
        )
    }
}


