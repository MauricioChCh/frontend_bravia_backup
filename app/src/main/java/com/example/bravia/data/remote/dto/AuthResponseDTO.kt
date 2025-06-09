package com.example.bravia.data.remote.dto

/**
 * DTOs representing authentication response
 */
data class AuthorityDto(
    val authority: String
)

data class AuthResponseDto(
    val token: String,
    val username: String,
    val userId: String,
    val authorities: List<AuthorityDto>
)
