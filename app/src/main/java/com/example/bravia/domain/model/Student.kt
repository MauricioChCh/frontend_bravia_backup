package com.example.bravia.domain.model

data class Student(
    val id: Long?,
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