package com.example.bravia.data.remote

import com.example.bravia.data.remote.api.AdminService
import com.example.bravia.data.remote.api.BusinessService
import com.example.bravia.data.remote.dto.CompanyResponseDTO
import retrofit2.Response
import javax.inject.Inject

class CompanyRemoteDataSource @Inject constructor(
    private val businessService: BusinessService,
    private val adminService: AdminService
) {


    suspend fun getCompanyById(username: String): Result<CompanyResponseDTO?> = safeApiCall {
        businessService.getCompanyById(username)
    }

    suspend fun getCompanyByCompanyId(id: Long): Result<CompanyResponseDTO?> = safeApiCall {
        adminService.getCompanyCompanyById(id)
    }

    /**
     * Helper function to handle API calls safely.
     *
     * @param apiCall The suspending function making the API call
     * @return [Result] containing the data if successful, or an exception if failed
     */
    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Result<T> = try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            } ?: Result.failure(Exception("Response body was null"))
        } else {
            val errorBody = response.errorBody()?.string()
            Result.failure(Exception("API error ${response.code()}: $errorBody"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}