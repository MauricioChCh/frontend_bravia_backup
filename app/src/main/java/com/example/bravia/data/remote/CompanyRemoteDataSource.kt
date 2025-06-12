package com.example.bravia.data.remote

import com.example.bravia.data.remote.api.AdminService
import com.example.bravia.data.remote.api.BusinessService
import com.example.bravia.data.remote.dto.CompanyResponseDTO
import com.example.bravia.data.remote.dto.TagDTO
import com.example.bravia.domain.model.CompanName
import com.example.bravia.domain.model.CompanyBusinessAreas
import com.example.bravia.domain.model.CompanyDescription
import com.example.bravia.domain.model.CompanyTags
import com.example.bravia.domain.model.Location
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

    suspend fun getAllTags(): Result<List<TagDTO>> = safeApiCall {
        businessService.getAllTags()
    }

    suspend fun updateCompanyDescription(company: CompanyDescription): Result<Unit> = safeApiCall {
        businessService.updateCompanyDescription(company.id, company)
    }

    suspend fun updateCompanyName(company: CompanName): Result<Unit> = safeApiCall {
        businessService.updateCompanyName(company.id, company)
    }

    suspend fun updateCompanyLocation(id: Long, location: Location): Result<Unit> = safeApiCall {
        businessService.updateCompanyLocation(id, location)
    }

    suspend fun updateCompanyTags(companyId: Long, companyTags: CompanyTags): Result<Unit> = safeApiCall {
        businessService.updateCompanyTags(companyId, companyTags)
    }

    suspend fun updateCompanyBusinessAreas(companyId: Long, businessAreas: CompanyBusinessAreas): Result<Unit> = safeApiCall {
        businessService.updateCompanyBusinessAreas(companyId, businessAreas)
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