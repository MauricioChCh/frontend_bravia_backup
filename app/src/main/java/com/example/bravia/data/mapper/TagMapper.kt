package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.TagDTO
import com.example.bravia.domain.model.Tag
import javax.inject.Inject

class TagMapper @Inject constructor() {

    fun mapToDomainList(dto: List<TagDTO>): List<Tag> {
        return dto.map { mapToDomain(it) }
    }

    fun mapToDTOList(domain: List<Tag>?): List<TagDTO> {
        return domain!!.map { mapToDto(it) }
    }


    fun mapToDomain(dto: TagDTO): Tag {
        return Tag(
            id = dto.id,
            name = dto.name
        )
    }

    fun mapToDto(domain: Tag): TagDTO {
        return TagDTO(
            id = domain.id,
            name = domain.name
        )
    }

}