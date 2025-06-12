package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.StudentResponseDTO
import com.example.bravia.data.remote.dto.UserResultResponse
import com.example.bravia.domain.model.Student
import com.example.bravia.domain.model.UserResult
import javax.inject.Inject

class UserResultMapper @Inject constructor(){
    fun mapToDomain(dto: UserResultResponse): UserResult {
        return UserResult(
            id = dto.id,
            email = dto.email,
            firstName = dto.firstName,
            lastName = dto.lastName,
        )
    }
}