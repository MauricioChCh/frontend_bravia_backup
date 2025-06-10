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
    @GET("users/signup/colleges")
    suspend fun getAllColleges(): Response<List<CollegeDTO>>

    /**
     * Retrieves a list of all available degrees from the remote API.
     *
     * @return [Response] containing a list of [BusinessAreaDTO] objects if successful
     */
    @GET("users/signup/business_areas")
    suspend fun getAllBusinessAreas(): Response<List<BusinessAreaDTO>>

    /**
     * Retrieves a list of all available interests from the remote API.
     *
     * @return [Response] containing a list of [InterestDTO] objects if successful
     */
    @GET("users/signup/interests")
    suspend fun getAllInterests(): Response<List<InterestDTO>>

    /**
     * Retrieves a specific interest by its ID from the remote API.
     *
     * @param id The ID of the interest to retrieve.
     * @return [Response] containing the [InterestDTO] object if successful, or null if not found
     */
    @GET("signup/1/interests/{id}")
    suspend fun getInterestById(id: Long): Response<InterestDTO?>

    /**
     * Retrieves a list of all available degrees from the remote API.
     *
     * @return [Response] containing a list of [DegreeDTO] objects if successful
     */
    @GET("users/signup/degrees")
    suspend fun getAllDegrees(): Response<List<DegreeDTO>>

}