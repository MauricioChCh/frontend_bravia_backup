package com.example.bravia.data.mapper

import com.example.bravia.data.datasource.model.InternshipDto
import com.example.bravia.domain.model.Internship

class InternshipMapper {
    fun mapToDomain(dto: InternshipDto, isBookmarked: Boolean = false): Internship {
        return Internship(
            id = dto.id,
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
            isBookmarked = isBookmarked
        )
    }

    fun mapToDto(domain: Internship): InternshipDto {
        return InternshipDto(
            id = domain.id,
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
            link = domain.link
        )
    }
}