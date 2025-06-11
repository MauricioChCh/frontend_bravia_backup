package com.example.bravia.data.remote

import android.util.Log
import com.example.bravia.data.remote.api.AdminService
import com.example.bravia.data.remote.dto.CompanyResponseDTO
import com.example.bravia.data.remote.dto.StudentResponseDTO
import com.example.bravia.data.remote.dto.UserReportResponseDTO
import com.example.bravia.data.remote.utils.ApiCallHandler.Companion.safeApiCall
import com.example.bravia.domain.model.UserReport
import javax.inject.Inject

class AdminRemoteDataSource @Inject constructor(
    private val adminService: AdminService
){
    // Cambié la firma para que devuelva lista y llamo el método correcto del servicio
    suspend fun getAllCompanies(): Result<List<CompanyResponseDTO>> = safeApiCall {
        val response = adminService.getAllCompanies()
        Log.d("AdminRemoteDataSource", "Llamada ejecutada con código: ${response.code()}")
        Log.d("AdminRemoteDataSource", "Es exitosa: ${response.isSuccessful}")
        Log.d("AdminRemoteDataSource", "Body: ${response.body()}")
        Log.d("AdminRemoteDataSource", "Body size: ${(response.body() as? List<*>)?.size}")
        response
    }

    suspend fun getAllStudents(): Result<List<StudentResponseDTO>> = safeApiCall {
        val response = adminService.getAllStudents()
        Log.d("AdminRemoteDataSource", "Llamada ejecutada con código: ${response.code()}")
        Log.d("AdminRemoteDataSource", "Es exitosa: ${response.isSuccessful}")
        Log.d("AdminRemoteDataSource", "Body: ${response.body()}")
        Log.d("AdminRemoteDataSource", "Body size: ${(response.body() as? List<*>)?.size}")
        response
    }

    suspend fun getAllUserReports(): Result<List<UserReportResponseDTO>> = safeApiCall {
        val response = adminService.getAllUserReports()
        Log.d("AdminRemoteDataSource", "Llamada ejecutada con código: ${response.code()}")
        Log.d("AdminRemoteDataSource", "Es exitosa: ${response.isSuccessful}")
        Log.d("AdminRemoteDataSource", "Body: ${response.body()}")
        Log.d("AdminRemoteDataSource", "Body size: ${(response.body() as? List<*>)?.size}")
        response
    }

    suspend fun getUserReportById(reportId: Long): Result<UserReportResponseDTO> = safeApiCall {
        val response = adminService.getUserReportById(reportId)
        Log.d("AdminRemoteDataSource", "Llamada ejecutada con código: ${response.code()}")
        Log.d("AdminRemoteDataSource", "Es exitosa: ${response.isSuccessful}")
        Log.d("AdminRemoteDataSource", "Body: ${response.body()}")
        response
    }

    suspend fun getStudentById(studentId: Long): Result<StudentResponseDTO> = safeApiCall {
        val response = adminService.getStudentById(studentId)
        Log.d("AdminRemoteDataSource", "getStudentById - Código: ${response.code()}")
        Log.d("AdminRemoteDataSource", "getStudentById - Éxito: ${response.isSuccessful}")
        Log.d("AdminRemoteDataSource", "getStudentById - Body: ${response.body()}")
        response
    }

    suspend fun getCompanyById(companyId: Long): Result<CompanyResponseDTO> = safeApiCall {
        val response = adminService.getCompanyById(companyId)
        Log.d("AdminRemoteDataSource", "getCompanyById - Código: ${response.code()}")
        Log.d("AdminRemoteDataSource", "getCompanyById - Éxito: ${response.isSuccessful}")
        Log.d("AdminRemoteDataSource", "getCompanyById - Body: ${response.body()}")
        response
    }

}