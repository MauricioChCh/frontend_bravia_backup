package com.example.bravia.data.remote.dto

data class StudentResponseDTO(
    val id: Long,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
)

data class StudentNewDTO(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val firstName: String,
    val lastName: String,
    val college: CollegeDTO,
    val degree: DegreeDTO,
    val interest: List<InterestDTO>,
)