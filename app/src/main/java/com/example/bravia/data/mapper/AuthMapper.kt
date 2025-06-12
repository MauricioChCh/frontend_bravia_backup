package com.example.bravia.data.mapper

import android.util.Log
import com.example.bravia.data.remote.dto.AuthRequestDto
import com.example.bravia.data.remote.dto.AuthResponseDto
import com.example.bravia.domain.model.AuthResult
import com.example.bravia.domain.model.Authority
import com.example.bravia.domain.model.Credentials

object AuthMapper {
    /**
     * Converts credentials to DTO
     */
    fun credentialsToDto(credentials: Credentials): AuthRequestDto {
        return AuthRequestDto(
            username = credentials.username,
            password = credentials.password
        )
    }

    /**
     * Converts authentication response DTO to domain model
     */
    fun dtoToAuthResult(dto: AuthResponseDto, token: String): AuthResult {
        Log.d("AuthMapper", "------------------------------- User roles: ${dto.authorities.joinToString(", ")}")
        Log.d("AuthMapper", " User roles: ${dto.userId}")
        Log.d("AuthMapper", " User roles: ${dto.username}")
        return AuthResult(
            token = token,
            userId = dto.userId,
            username = dto.username, authorities = dto.authorities.map { authorityDto -> Authority(authorityDto.authority) } // Convert AuthorityDto to String
        )
    }
}
