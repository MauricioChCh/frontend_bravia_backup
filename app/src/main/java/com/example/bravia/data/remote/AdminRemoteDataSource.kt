package com.example.bravia.data.remote

import android.util.Log
import com.example.bravia.data.remote.api.AdminService
import com.example.bravia.data.remote.dto.AdminBanningStudentRequestDTO
import com.example.bravia.data.remote.dto.CompanyResponseDTO
import com.example.bravia.data.remote.dto.StudentResponseAdminDTO
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
        Log.d("AdminRemoteDataSourceGetAllCompanies()", "Llamada ejecutada con código: ${response.code()}")
        Log.d("AdminRemoteDataSourceGetAllCompanies()", "Es exitosa: ${response.isSuccessful}")
        Log.d("AdminRemoteDataSourceGetAllCompanies()", "Body mensaje: ${response.message()}")
        Log.d("AdminRemoteDataSourceGetAllCompanies()", "Body size: ${(response.body() as? List<*>)?.size}")
        response
    }

    suspend fun getAllStudents(): Result<List<StudentResponseAdminDTO>> = safeApiCall {
        val response = adminService.getAllStudents()
        Log.d("AdminRemoteDataSourceGetAllStudents", "Llamada ejecutada con código: ${response.code()}")
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

    suspend fun getStudentById(studentId: Long): Result<StudentResponseAdminDTO> = safeApiCall {
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

    suspend fun banStudent(userId: Long, banStatus: Boolean): Result<Unit> = safeApiCall {
        Log.d("AdminRemoteDataSource", "=== INICIO banStudent ===")
        Log.d("AdminRemoteDataSource", "banStudent - Input userId: $userId")
        Log.d("AdminRemoteDataSource", "banStudent - Input banStatus: $banStatus")

        // Crear el DTO con los parámetros recibidos
        val banRequest = AdminBanningStudentRequestDTO(
            userId = userId,
            bannStatus = banStatus
        )

        Log.d("AdminRemoteDataSource", "banStudent - DTO creado:")
        Log.d("AdminRemoteDataSource", "banStudent - DTO.userId: ${banRequest.userId}")
        Log.d("AdminRemoteDataSource", "banStudent - DTO.bannStatus: ${banRequest.bannStatus}")

        Log.d("AdminRemoteDataSource", "banStudent - Calling adminService.banStudent...")

        try {
            val response = adminService.banStudent(banRequest)

            Log.d("AdminRemoteDataSource", "banStudent - Response recibida:")
            Log.d("AdminRemoteDataSource", "banStudent - Código: ${response.code()}")
            Log.d("AdminRemoteDataSource", "banStudent - Éxito: ${response.isSuccessful}")
            Log.d("AdminRemoteDataSource", "banStudent - Message: ${response.message()}")
            Log.d("AdminRemoteDataSource", "banStudent - Headers: ${response.headers()}")
            Log.d("AdminRemoteDataSource", "banStudent - Raw response: ${response.raw()}")
            Log.d("AdminRemoteDataSource", "banStudent - Body: ${response.body()}")

            if (response.raw().request.url != null) {
                Log.d("AdminRemoteDataSource", "banStudent - URL llamada: ${response.raw().request.url}")
                Log.d("AdminRemoteDataSource", "banStudent - Método HTTP: ${response.raw().request.method}")
            }

            if (!response.isSuccessful) {
                Log.e("AdminRemoteDataSource", "banStudent - ERROR: Response no exitosa")
                throw Exception("Error al banear estudiante con ID $userId: ${response.message()}")
            }

            Log.d("AdminRemoteDataSource", "banStudent - SUCCESS: Response exitosa")
            Log.d("AdminRemoteDataSource", "=== FIN banStudent ===")

            // Retorna el response en lugar de Unit
            response

        } catch (e: Exception) {
            Log.e("AdminRemoteDataSource", "banStudent - EXCEPTION caught:", e)
            Log.e("AdminRemoteDataSource", "banStudent - Exception message: ${e.message}")
            Log.e("AdminRemoteDataSource", "banStudent - Exception type: ${e.javaClass.simpleName}")
            throw e
        }
    }
}