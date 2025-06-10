package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.CompanyNewDTO
import com.example.bravia.data.remote.dto.CompanyResponseDTO
import com.example.bravia.data.remote.dto.UserDTO
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.CompanyNew
import javax.inject.Inject

class CompanyMapper @Inject constructor(){

    private val cityMapper = CityMapper()
    private val countryMapper = CountryMapper()
    private val locationMapper = LocationMapper(cityMapper, countryMapper)
    private val tagMapper = TagMapper()
    private val businessAreaMapper = BusinessAreaMapper()
    private val contactMapper = ContactMapper()

    fun mapToDomain(dto: CompanyResponseDTO): Company {
        return Company(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            imageUrl = dto.imageUrl,
            businessAreas = dto.businessAreas.map { businessAreaMapper.mapToDomain(it) },
            tags = dto.tags.map { tagMapper.mapToDomain(it) },
            location = locationMapper.mapToDomain(dto.location),
            contacts = dto.contacts.map { contactMapper.mapToDomain(it) },
            firstName = dto.firstName,
            lastName = dto.lastName,
            email = dto.email,
            verified = dto.verified,
        )
    }

    fun mapToNewDTO(company: CompanyNew): CompanyNewDTO {
        return CompanyNewDTO(
            user = UserDTO(company.email, company.password, company.firstName, company.lastName),
            name = company.name,
            businessArea = businessAreaMapper.mapToDto(company.businessArea),
        )
    }

    fun mapToDto(domain: Company): CompanyResponseDTO {
        return CompanyResponseDTO(
            id = domain.id,
            name = domain.name,
            description = domain.description,
            imageUrl = domain.imageUrl,
            businessAreas = domain.businessAreas.map { businessAreaMapper.mapToDto(it) },
            tags = tagMapper.mapToDTOList(domain.tags),
            location = locationMapper.mapToDto(domain.location),
            contacts = contactMapper.mapToDTOList(domain.contacts),
            firstName = domain.firstName,
            lastName = domain.lastName,
            email = domain.email,
            verified = domain.verified,
        )
    }
}