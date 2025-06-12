package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.CertificationDTO
import com.example.bravia.domain.model.Certification
import javax.inject.Inject

class CertificationMapper @Inject constructor() {
    fun mapToDomain(dto: CertificationDTO): Certification {
        return Certification(
            id = dto.id,
            name = dto.name,
            date = dto.date,
            organization = dto.organization
        )
    }
}