package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.AuthResult
import com.example.bravia.domain.repository.AuthRepository
import jakarta.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<AuthResult> {
        return authRepository.login(email, password)
    }
}
