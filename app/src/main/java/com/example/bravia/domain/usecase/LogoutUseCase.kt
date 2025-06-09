package com.example.bravia.domain.usecase

import com.example.bravia.domain.repository.AuthRepository
import jakarta.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(): Result<Unit> {
        return authRepository.logout()
    }
}