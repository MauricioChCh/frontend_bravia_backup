package com.example.bravia.data.remote.api

import com.example.bravia.data.remote.dto.CityDTO
import com.example.bravia.data.remote.dto.CompanyResponseDTO
import com.example.bravia.data.remote.dto.CountryDTO
import com.example.bravia.data.remote.dto.InternshipDTO
import com.example.bravia.data.remote.dto.LocationDTO
import com.example.bravia.data.remote.dto.ModalityDTO
import com.example.bravia.data.remote.dto.NewInternshipDTO
import com.example.bravia.data.remote.dto.UpdateInternshipDTO
import com.example.bravia.domain.model.Country
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface BusinessService {
    /**
     * Retrieves a list of internships for a specific business and company from the remote API.
     *
     * @param bId The ID of the business.
     * @return [Response] containing a list of [InternshipDTO] objects if successful
     */
    @GET("companies/{username}/internships")
    suspend fun getAllBusinessInternships(@Path("username") username: String): Response<List<InternshipDTO>>

    /**
     * Retrieves a specific business internship by its ID from the remote API.
     *
     * @param id The ID of the internship to retrieve.
     * @return [Response] containing the [InternshipDTO] object if successful
     */
    @GET("companies/{username}/internships/{internshipId}")
    suspend fun getBusinessInternshipById(@Path("username") username: String, @Path("internshipId") internshipId: Long): Response<InternshipDTO?>

    @GET("companies/{username}")
    suspend fun getCompanyById(@Path("username") username: String): Response<CompanyResponseDTO?>

    @GET("companies/{username}/locations")
    suspend fun getAllBusinessLocations(@Path("username") username: String): Response<List<LocationDTO>>

    @GET("companies/internships/modalities")
    suspend fun getAllInternshipsModalities(): Response<List<ModalityDTO>>

    @POST("internships")
    suspend fun newInternship(@Body internship: NewInternshipDTO): Response<InternshipDTO?>

    @PATCH("companies/{username}/internships")
    suspend fun updateInternship(@Path("username") username: String, @Body internship: UpdateInternshipDTO): Response<InternshipDTO?>

    @GET("companies/cities")
    suspend fun getAllCities(): Response<List<CityDTO>>

    @GET("companies/countries")
    suspend fun getAllCountries(): Response<List<CountryDTO>>

}