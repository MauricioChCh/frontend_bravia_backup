package com.example.bravia.data.remote.dto

import com.example.bravia.domain.model.Authority

data class AuthResult(
    val token: String,
    val userId: String,
    val username: String,
    val authorities: List<Authority>
)