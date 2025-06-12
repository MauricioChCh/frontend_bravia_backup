package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.InternshipDTO
import com.example.bravia.data.remote.dto.NewInternshipDTO
import com.example.bravia.data.remote.dto.UpdateInternshipDTO
import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.model.NewInternship
import com.example.bravia.domain.model.UpdateInternship
import javax.inject.Inject

class InternshipMapper @Inject constructor()  {
    fun mapToDomain(dto: InternshipDTO): Internship {
        return Internship(
            id = dto.id,
            company = dto.companyName,
            city = dto.cityName,
            country = dto.countryName,
            title = dto.title,
            description = dto.description,
            imageUrl = dto.imageUrl,
            publicationDate = dto.publicationDate,
            duration = dto.duration,
            salary = dto.salary,
            modality = dto.modality,
            schedule = dto.schedule,
            requirements = dto.requirements,
            activities = dto.activities,
            link = dto.link,
            isMarked = dto.isBookmarked
        )
    }

//    fun mapToDto(domain: Internship): InternshipDTO {
//        return InternshipDTO(
//            id = domain.id,
//            companyId = domain.companyId,
//            title = domain.title,
//            company = domain.company,
//            description = domain.description,
//            imageUrl = domain.imageUrl,
//            location = domain.location,
//            publicationDate = domain.publicationDate,
//            duration = domain.duration,
//            salary = domain.salary,
//            modality = domain.modality,
//            schedule = domain.schedule,
//            requirements = domain.requirements,
//            percentage = domain.percentage,
//            activities = domain.activities,
//            contact = domain.contact,
//            link = domain.link,
//            isMarked = domain.isMarked
//        )
//    }

    fun mapToNewDTO(domain: NewInternship): NewInternshipDTO {
        return NewInternshipDTO(
            companyId = domain.company,
            locationId = domain.location,
            title = domain.title,
            description = domain.description,
            imageUrl = domain.imageUrl,
            publicationDate = domain.publicationDate,
            duration = domain.duration,
            salary = domain.salary,
            modality = domain.modality,
            schedule = domain.schedule,
            requirements = domain.requirements,
            activities = domain.activities,
            link = domain.link
        )
    }

    fun mapToUpdateDTO(domain: UpdateInternship) : UpdateInternshipDTO{
        return UpdateInternshipDTO(
            id = domain.id,
            company = domain.company,
            location = domain.location,
            title = domain.title,
            description = domain.description,
            imageUrl = domain.imageUrl,
            publicationDate = domain.publicationDate,
            duration = domain.duration,
            salary = domain.salary,
            modality = domain.modality,
            schedule = domain.schedule,
            requirements = domain.requirements,
            activities = domain.activities,
            link = domain.link,
        )
    }
}