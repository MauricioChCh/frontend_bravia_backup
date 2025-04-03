package com.example.bravia.data.remote

import com.example.bravia.data.remote.api.SignUpService
import com.example.bravia.data.remote.dto.InterestDTO
import retrofit2.Response
import javax.inject.Inject

class InterestRemoteDataSource @Inject constructor (
    private val signUpService: SignUpService
) {


    /**
     * Retrieves a list of all available interests from the remote API.
     *
     * @return [Result] containing a list of [InterestDTO] objects if successful
     * or an exception if the operation failed
     */
    suspend fun getAllInterests(): Result<List<InterestDTO>> = safeApiCall {
        signUpService.getAllInterests()
    }

    suspend fun getInterestById(id: Long): Result<InterestDTO?> = safeApiCall {
        signUpService.getInterestById(id)
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