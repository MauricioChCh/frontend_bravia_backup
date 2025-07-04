package com.example.bravia.data.remote

import com.example.bravia.data.remote.api.BusinessService
import com.example.bravia.data.remote.dto.ModalityDTO
import retrofit2.Response
import javax.inject.Inject

class ModalityRemoteDataSource @Inject constructor(
  private val businessService: BusinessService
) {
    suspend fun getAllInternshipsModalities(): Result<List<ModalityDTO?>> = safeApiCall {
        businessService.getAllInternshipsModalities()
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