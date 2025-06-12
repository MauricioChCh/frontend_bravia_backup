package com.example.bravia.domain.usecase

import android.util.Log
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.repository.AdminRepository
import javax.inject.Inject

class GetAdminCompanyByIdUseCase @Inject constructor(
    private val repository: AdminRepository
) {
    suspend operator fun invoke(companyId: Long): Result<Company> {
        return try {
            val company = repository.findCompanyById(companyId)
            Log.d(
                "GetAdminCompanyByIdUseCase",
                "Fetched company id: ${company.id}, name: ${company.name}"
            )
            Result.success(company)
        } catch (e: Exception) {
            Log.e(
                "GetAdminCompanyByIdUseCase",
                "Error fetching company with id $companyId",
                e
            )
            Result.failure(e)
        }
    }
}