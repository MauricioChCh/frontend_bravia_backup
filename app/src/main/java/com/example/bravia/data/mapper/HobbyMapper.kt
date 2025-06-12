package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.HobbyDTO
import com.example.bravia.domain.model.Hobby
import javax.inject.Inject
class HobbyMapper@Inject constructor(){

    // Mapper for Hobby
    fun mapToDomain(dto: HobbyDTO): Hobby {
        return Hobby(
            id = dto.id,
            name = dto.name
        )
    }


}