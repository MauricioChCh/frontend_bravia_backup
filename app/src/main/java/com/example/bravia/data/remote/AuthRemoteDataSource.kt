package com.example.bravia.data.remote

import android.util.Log
import com.example.bravia.data.mapper.AuthMapper
import com.example.bravia.data.remote.api.AuthService
import com.example.bravia.data.remote.dto.CompanyNewDTO
import com.example.bravia.data.remote.dto.CompanyResponseDTO
import com.example.bravia.data.remote.dto.StudentNewDTO
import com.example.bravia.data.remote.dto.StudentResponseDTO
import com.example.bravia.domain.model.AuthResult
import com.example.bravia.domain.model.Authority
import com.example.bravia.domain.model.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

/**
 * Remote data source for authentication operations.
 * Handles all network operations related to authentication using [AuthService].
 */
class AuthRemoteDataSource @Inject constructor(
    private val authService: AuthService
) {
    /**
     * Authenticates a user with provided credentials
     *
     * @param credentials The user credentials
     * @return [Result] containing [AuthResult] if successful, or an exception if failed
     */
    //Vercion un poco diferente a la del profe, maneja errores de manera separada y maneja roles de usuario
    suspend fun login(credentials: Credentials): Result<AuthResult> = withContext(Dispatchers.IO) {
        try {
            // Convert credentials and log the request payload for debugging
            val requestDto = AuthMapper.credentialsToDto(credentials)
            Log.d(
                "AuthRemoteDataSource",
                "Login request payload: username=${requestDto.username}, password length=${requestDto.password.length}"
            )

            val response = authService.login(requestDto)

            if (!response.isSuccessful) {
                // Manejo de errores HTTP
                return@withContext handleErrorResponse(response)
            }
            // Verificar el header Authorization
            val authHeader = response.headers()["Authorization"]
                ?: return@withContext Result.failure(Exception("Missing Authorization header"))
            //Verificamos que el header Authorization tenga el formato correcto
            if (!authHeader.startsWith("Bearer ")) {
                return@withContext Result.failure(Exception("Invalid token format"))
            }
            // Extraer el token del header Authorization
            val token = authHeader.substring(7) // Remove "Bearer "

            // Obtener datos del body
            val body = response.body() ?: return@withContext Result.failure(Exception("Empty response body"))

            // 3. Construir AuthResult
            val authResult = AuthResult(
                token = token, // Del header
                userId = body.userId,
                username = body.username,
                authorities = body.authorities.map { Authority(it.authority) }
            )

            Log.d("AuthRemote", "Login success - Token: ${token.take(5)}...")
            Result.success(authResult)
        } catch (e: Exception) {
            Log.e("AuthRemote", "Login error", e)
            Result.failure(e)
        }

    }

private fun handleErrorResponse(response: Response<*>): Result<AuthResult> {
    val errorMsg = when (response.code()) {
        403 -> "Invalid username or password"
        401 -> "Unauthorized access"
        404 -> "Service not found"
        500 -> "Server error occurred"
        else -> "Error ${response.code()}: ${response.errorBody()?.string()}"
    }
    Log.e(
        "AuthRemoteDataSource",
        "Login failed: $errorMsg"
    )

    return Result.failure(Exception(errorMsg))
}
    /**
     * Logs out the current user
     *
     * @return [Result] with success or failure
     */
    suspend fun logout(): Result<Unit> = safeApiCall {
        authService.logout()
    }


    suspend fun registerBusiness(company: CompanyNewDTO): Result<CompanyResponseDTO> = safeApiCall {
        authService.registerBusiness(company)
    }

    suspend fun registerStudent(student: StudentNewDTO): Result<StudentResponseDTO> = safeApiCall {
        authService.registerStudent(student)
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
            // Improved error handling
            val errorMessage = when (response.code()) {
                403 -> "Invalid username or password"
                401 -> "Unauthorized access"
                else -> "API error ${response.code()}: $errorBody"
            }
            Result.failure(Exception(errorMessage))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}