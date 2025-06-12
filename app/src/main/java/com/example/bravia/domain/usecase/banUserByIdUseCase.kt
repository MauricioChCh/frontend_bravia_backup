package com.example.bravia.domain.usecase

import android.util.Log
import com.example.bravia.data.remote.dto.AdminBanningStudentRequestDTO
import com.example.bravia.domain.repository.AdminRepository
import javax.inject.Inject

class BanUserIdUseCase @Inject constructor(
    private val repository: AdminRepository
) {
    suspend operator fun invoke(userId: Long, isBanned: Boolean): Result<Unit> {
        return try {
            repository.banStudent(userId, isBanned)
            Log.d(
                "BanUserIdUseCase",
                "Updated ban status for userId: $userId to banned: $isBanned"
            )
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(
                "BanUserIdUseCase",
                "Error updating ban status for userId: $userId",
                e
            )
            Result.failure(e)
        }
    }
}