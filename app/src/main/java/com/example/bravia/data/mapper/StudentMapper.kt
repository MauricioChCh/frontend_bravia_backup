package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.StudentNewDTO
import com.example.bravia.data.remote.dto.StudentResponseDTO
import com.example.bravia.domain.model.Student
import com.example.bravia.domain.model.StudentNew
import javax.inject.Inject

class StudentMapper @Inject constructor(
    private val collegeMapper: CollegeMapper,
    private val degreeMapper: DegreeMapper,
    private val interestMapper: InterestMapper,
){

    fun mapToDomain(dto: StudentResponseDTO): Student {
        return Student(
            id = dto.id,
            firstName = dto.firstName,
            lastName = dto.lastName,
            email = dto.email,
        )
    }

    fun mapToNewDTO(student: StudentNew): StudentNewDTO {
        return StudentNewDTO(
            email = student.email,
            password = student.password,
            confirmPassword = student.confirmPassword,
            firstName = student.firstName,
            lastName = student.lastName,
            college = collegeMapper.mapToDto(student.college),
            degree = degreeMapper.mapToDto(student.degree),
            interest = student.interest.map { interestMapper.mapToDto(it) },
        )
    }


}