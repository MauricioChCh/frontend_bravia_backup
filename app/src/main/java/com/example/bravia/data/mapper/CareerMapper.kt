package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.CareerDTO
import com.example.bravia.domain.model.Career
import jakarta.inject.Inject

class CareerMapper @Inject constructor() {
    // Mapper for Career
    fun mapToDomain( dto: CareerDTO ): Career {
        return Career(
            id = dto.id,
            career = dto.career
        )
    }
}