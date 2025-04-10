package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.InternshipDTO
import com.example.bravia.domain.model.Internship
import javax.inject.Inject

class InternshipMapper @Inject constructor()  {
    fun mapToDomain(dto: InternshipDTO, isMarked: Boolean = false): Internship {
        return Internship(
            id = dto.id,
            companyId = dto.companyId,
            title = dto.title,
            company = dto.company,
            description = dto.description,
            imageUrl = dto.imageUrl,
            location = dto.location,
            publicationDate = dto.publicationDate,
            duration = dto.duration,
            salary = dto.salary,
            modality = dto.modality,
            schedule = dto.schedule,
            requirements = dto.requirements,
            percentage = dto.percentage,
            activities = dto.activities,
            contact = dto.contact,
            link = dto.link,
            isMarked = isMarked
        )
    }

    fun mapToDto(domain: Internship): InternshipDTO {
        return InternshipDTO(
            id = domain.id,
            companyId = domain.companyId,
            title = domain.title,
            company = domain.company,
            description = domain.description,
            imageUrl = domain.imageUrl,
            location = domain.location,
            publicationDate = domain.publicationDate,
            duration = domain.duration,
            salary = domain.salary,
            modality = domain.modality,
            schedule = domain.schedule,
            requirements = domain.requirements,
            percentage = domain.percentage,
            activities = domain.activities,
            contact = domain.contact,
            link = domain.link,
            isMarked = domain.isMarked
        )
    }
}