package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.ContactDTO
import com.example.bravia.domain.model.Contact
import javax.inject.Inject

class ContactMapper @Inject constructor() {

    fun mapToDomainList(dto: List<ContactDTO>): List<Contact> {
        return dto.map { mapToDomain(it) }
    }

    fun mapToDTOList(domain: List<Contact>?): List<ContactDTO> {
        return domain!!.map { mapToDto(it) }
    }


    fun mapToDomain(dto: ContactDTO): Contact {
        return Contact(
            id = dto.id,
            url = dto.url,
        )
    }

    fun mapToDto(domain: Contact): ContactDTO {
        return ContactDTO(
            id = domain.id,
            url = domain.url,
        )
    }

}