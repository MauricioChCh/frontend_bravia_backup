package com.example.bravia.domain.usecase

import android.util.Log
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.repository.AdminRepository
import javax.inject.Inject



/*
class GetAllCompaniesUseCase @Inject constructor(
    private val repository: CompanyRepository
) {
    suspend operator fun invoke(): Result<List<Company>> {
        return try {
            val companies = repository.findAll()
            Result.success(companies)
        } catch (e: Exception) {

            Result.failure(e)
        }
    }
}*/

class GetAllCompaniesUseCase @Inject constructor(
    private val repository: AdminRepository
) {
    suspend operator fun invoke(): Result<List<Company>> {
        return try {
            val companies = repository.findAllCompanies()
            Log.d("GetAllCompaniesUseCase", "Fetched companies count: ${companies.size}")
            companies.forEach { company ->
                Log.d("GetAllCompaniesUseCase", "Company -> id: ${company.id}, name: ${company.name}, location: ${company.location.toString()}")
            }
            Result.success(companies)
        } catch (e: Exception) {
            Log.e("GetAllCompaniesUseCase", "Error fetching companies", e)
            Result.failure(e)
        }
    }
}
