package com.example.bravia.data.remote.api

import com.example.bravia.data.remote.dto.CompanyResponseDTO
import com.example.bravia.data.remote.dto.InternshipDTO
import com.example.bravia.data.remote.dto.LocationDTO
import com.example.bravia.data.remote.dto.NewInternshipDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BusinessService {
    /**
     * Retrieves a list of internships for a specific business and company from the remote API.
     *
     * @param bId The ID of the business.
     * @return [Response] containing a list of [InternshipDTO] objects if successful
     */
//    @GET("business/{businessId}/internships") // TODO: we have to use this endpoint
    @GET("internships")
    suspend fun getAllBusinessInternships(@Path("businessId") businessId: Long): Response<List<InternshipDTO>>

    /**
     * Retrieves a specific business internship by its ID from the remote API.
     *
     * @param id The ID of the internship to retrieve.
     * @return [Response] containing the [InternshipDTO] object if successful
     */
    @GET("business/{businessId}/internships/{internshipId}")
    suspend fun getBusinessInternshipById(@Path("businessId") businessId: Long, @Path("internshipId") internshipId: Long): Response<InternshipDTO?>

    @GET("companies/{companyId}")
    suspend fun getCompanyById(@Path("companyId") companyId: Long): Response<CompanyResponseDTO?>

    @GET("companies/{companyId}/locations")
    suspend fun getAllBusinessLocations(@Path("companyId") companyId: Long): Response<List<LocationDTO>>

    @POST("internships")
    suspend fun newInternship(@Body internship: NewInternshipDTO): Response<InternshipDTO?>
}