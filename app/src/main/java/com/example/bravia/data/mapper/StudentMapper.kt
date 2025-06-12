package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.StudentResponseDTO
import com.example.bravia.domain.model.Student
import javax.inject.Inject

class StudentMapper @Inject constructor(
    private val userResultMapper: UserResultMapper
){
    fun mapToDomain(dto: StudentResponseDTO): Student {
        return Student(
            id = dto.id,
            description = dto.description,
            academicCenter = dto.academicCenter,
            userInput = dto.userInput
        )
    }
}