package com.example.bravia.data.remote.api

import com.example.bravia.data.remote.dto.AuthRequestDto
import com.example.bravia.data.remote.dto.AuthResponseDto
import com.example.bravia.data.remote.dto.CompanyNewDTO
import com.example.bravia.data.remote.dto.CompanyResponseDTO
import com.example.bravia.data.remote.dto.StudentNewDTO
import com.example.bravia.data.remote.dto.StudentResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    /**
     * Authenticates a user with the provided credentials
     *
     * @param request Authentication credentials
     * @return Authentication response containing token
     */
    @POST("users/login")
    suspend fun login(@Body request: AuthRequestDto): Response<AuthResponseDto>

    /**
     * Logs out the current user session
     */
    @POST("users/logout") //TODO: Pending in backend
    suspend fun logout(): Response<Unit>


    @POST("users/signup/company")
    suspend fun registerBusiness(@Body business: CompanyNewDTO): Response<CompanyResponseDTO>

    @POST("users/signup/student")
    suspend fun registerStudent(@Body student: StudentNewDTO): Response<StudentResponseDTO>
}