package com.example.bravia.domain.usecase

import android.util.Log
import com.example.bravia.domain.model.UserReport
import com.example.bravia.domain.repository.AdminRepository
import javax.inject.Inject

class GetUserReportByIdUseCase @Inject constructor(
    private val repository: AdminRepository
) {
    suspend operator fun invoke(reportId: Long): Result<UserReport> {
        return try {
            val userReport = repository.findUserReportById(reportId)
            Log.d("GetUserReportByIdUseCase", "Fetched user report id: ${userReport.id}, ReporterName: ${userReport.reporterName}, description: ${userReport.description}")
            Result.success(userReport)
        } catch (e: Exception) {
            Log.e("GetUserReportByIdUseCase", "Error fetching user report with id $reportId", e)
            Result.failure(e)
        }
    }
}