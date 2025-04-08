package com.example.bravia.data.remote.utils

import retrofit2.Response

class ApiCallHandler {
    companion object {
        // Función general para cualquier tipo
        suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Result<T> = try {
            val response = apiCall()
            handleResponse(response)
        } catch (e: Exception) {
            Result.failure(e)
        }



        // Función auxiliar para procesar respuestas
        private fun <T> handleResponse(response: Response<T>): Result<T> {
            return if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Response body was null"))
            } else {
                val errorBody = response.errorBody()?.string()
                Result.failure(Exception("API error ${response.code()}: $errorBody"))
            }
        }
    }
}