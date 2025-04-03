package com.example.bravia.data.remote.api

import com.example.bravia.data.remote.dto.BusinessAreaDTO
import com.example.bravia.data.remote.dto.CollegeDTO
import com.example.bravia.data.remote.dto.DegreeDTO
import com.example.bravia.data.remote.dto.InterestDTO
import retrofit2.Response
import retrofit2.http.GET


interface SignUpService {

    /**
     * Retrieves a list of all available degrees from the remote API.
     *
     * @return [Response] containing a list of [DegreeDTO] objects if successful
     */
    @GET("signup/colleges")
    suspend fun getAllColleges(): Response<List<CollegeDTO>>

    /**
     * Retrieves a list of all available degrees from the remote API.
     *
     * @return [Response] containing a list of [BusinessAreaDTO] objects if successful
     */
    @GET("signup/businessAreas")
    suspend fun getAllBusinessAreas(): Response<List<BusinessAreaDTO>>

    /**
     * Retrieves a list of all available interests from the remote API.
     *
     * @return [Response] containing a list of [InterestDTO] objects if successful
     */
    @GET("signup/interests")
    suspend fun getAllInterests(): Response<List<InterestDTO>>

    @GET("signup/interests/{id}")
    suspend fun getInterestById(id: Long): Response<InterestDTO?>

    /**
     * Retrieves a list of all available degrees from the remote API.
     *
     * @return [Response] containing a list of [DegreeDTO] objects if successful
     */
    @GET("signup/degrees")
    suspend fun getAllDegrees(): Response<List<DegreeDTO>>

}