package com.example.bravia.domain.usecase

import android.util.Log
import com.example.bravia.domain.model.Student
import com.example.bravia.domain.model.UserReport
import com.example.bravia.domain.repository.AdminRepository
import javax.inject.Inject

class GetAllUserReportsUseCase @Inject constructor(
    private val repository: AdminRepository
) {
    suspend operator fun invoke(): Result<List<UserReport>> {
        return try {
            val userReports = repository.findAllUserReports()
            Log.d("GetAllUserReportsUseCase", "Fetched students count: ${userReports.size}")
            userReports.forEach { userReport ->
                Log.d("GetAllUserReportsUseCase", "student -> id: ${userReport.id}, ReporterName: ${userReport.reporterName}, description: ${userReport.description}")
            }
            Result.success(userReports)
        } catch (e: Exception) {
            Log.e("GetAllUserReportsUseCase", "Error fetching students", e)
            Result.failure(e)
        }
    }
}