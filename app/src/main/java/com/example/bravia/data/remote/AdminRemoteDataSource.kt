package com.example.bravia.data.remote

import android.util.Log
import com.example.bravia.data.remote.api.AdminService
import com.example.bravia.data.remote.dto.CompanyResponseDTO
import com.example.bravia.data.remote.utils.ApiCallHandler.Companion.safeApiCall
import javax.inject.Inject

class AdminRemoteDataSource @Inject constructor(
    private val adminService: AdminService
){
    // Cambié la firma para que devuelva lista y llamo el método correcto del servicio
    suspend fun getAllCompanies(): Result<List<CompanyResponseDTO>> = safeApiCall {
        val response = adminService.getAllCompanies()
        Log.d("CompanyRemoteDataSource", "Llamada ejecutada con código: ${response.code()}")
        Log.d("CompanyRemoteDataSource", "Es exitosa: ${response.isSuccessful}")
        Log.d("CompanyRemoteDataSource", "Body: ${response.body()}")
        Log.d("CompanyRemoteDataSource", "Body size: ${(response.body() as? List<*>)?.size}")
        response
    }


}