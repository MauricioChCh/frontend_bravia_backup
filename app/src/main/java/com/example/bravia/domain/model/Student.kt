package com.example.bravia.domain.model

data class Student(
    val id: Long?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val description: String,
    val academicCenter: String,
    val userInput: UserResult
)

data class UserResult(
    var id: Long? = null,
    var email: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
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

