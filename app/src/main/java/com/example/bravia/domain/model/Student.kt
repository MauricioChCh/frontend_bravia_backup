package com.example.bravia.domain.model

data class Student(
    val id: Long?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
)



data class StudentNew(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val firstName: String,
    val lastName: String,
    val college: College,
    val degree: Degree,
    val interest: List<Interest>,
)

