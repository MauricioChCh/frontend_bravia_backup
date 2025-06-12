package com.example.bravia.data.remote.dto

import com.example.bravia.domain.model.UserResult
import com.google.gson.annotations.SerializedName


data class StudentResponseAdminDTO(
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

data class AdminBanningStudentRequestDTO(
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("bannStatus")
    val bannStatus: Boolean
)