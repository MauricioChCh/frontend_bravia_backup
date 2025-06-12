package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.EducationDTO
import com.example.bravia.domain.model.Education
import com.example.bravia.domain.model.Institution
import javax.inject.Inject

class EducationMapper @Inject constructor(
    private val institutionMapper: InstitutionMapper
) {
    fun mapToDomain(dto: EducationDTO): Education {
        return Education(
            id = dto.id,
            institution = dto.institution as Institution?,
            degree = dto.degree,
            startDate = dto.startDate,
            endDate = dto.endDate
        )
    }
}