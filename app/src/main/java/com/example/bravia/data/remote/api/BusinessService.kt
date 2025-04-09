package com.example.bravia.data.remote.api

import com.example.bravia.data.remote.dto.InternshipDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BusinessService {
    /**
     * Retrieves a list of all business internships from the remote API.
     *
     * @param id The ID of the business area for which to retrieve internships.
     * @return [Response] containing a list of [InternshipDTO] objects if successful
     */
    @GET("business/1/internships/{companyId}")
    suspend fun getAllBusinessInternships(@Path("companyId") id: Long): Response<List<InternshipDTO>>

    /**
     * Retrieves a specific business internship by its ID from the remote API.
     *
     * @param id The ID of the internship to retrieve.
     * @return [Response] containing the [InternshipDTO] object if successful
     */
    @GET("business/1/internship/{id}")
    suspend fun getBusinessInternshipById(@Path("id") id: Long): Response<InternshipDTO?>
}