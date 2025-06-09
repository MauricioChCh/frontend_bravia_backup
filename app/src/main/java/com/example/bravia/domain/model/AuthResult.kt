package com.example.bravia.domain.model

data class Authority(
    val authority: String
)

data class AuthResult(
    val token: String,
    val userId: String = "",
    val username: String = "",
    val authorities: List<Authority> = emptyList()
)