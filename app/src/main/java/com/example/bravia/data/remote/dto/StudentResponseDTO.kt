package com.example.bravia.data.remote.dto

import com.example.bravia.domain.model.UserResult

data class StudentResponseDTO(
    val id: Long?,
    val description: String,
    val academicCenter: String,
    val userInput: UserResult
)

data class UserResultResponse(
    var id: Long? = null,
    var email: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
)