package com.example.bravia.data.remote.api

import com.example.bravia.data.remote.dto.CityDTO
import com.example.bravia.data.remote.dto.CompanyResponseDTO
import com.example.bravia.data.remote.dto.CountryDTO
import com.example.bravia.data.remote.dto.InternshipDTO
import com.example.bravia.data.remote.dto.LocationDTO
import com.example.bravia.data.remote.dto.ModalityDTO
import com.example.bravia.data.remote.dto.NewInternshipDTO
import com.example.bravia.data.remote.dto.TagDTO
import com.example.bravia.data.remote.dto.UpdateInternshipDTO
import com.example.bravia.domain.model.CompanName
import com.example.bravia.domain.model.CompanyBusinessAreas
import com.example.bravia.domain.model.CompanyDescription
import com.example.bravia.domain.model.CompanyTags
import com.example.bravia.domain.model.Country
import com.example.bravia.domain.model.Location
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

    @GET("companies/tags")
    suspend fun getAllTags(): Response<List<TagDTO>>

    @PATCH("companies/{id}/description")
    suspend fun updateCompanyDescription(
        @Path("id") id: Long,
        @Body companyDescription: CompanyDescription
    ): Response<Unit>

    @PATCH("companies/{id}/name")
    suspend fun updateCompanyName(
        @Path("id") id: Long,
        @Body companyName: CompanName
    ): Response<Unit>

    @PATCH("companies/{id}/location")
    suspend fun updateCompanyLocation(
        @Path("id") id: Long,
        @Body location: Location
    ): Response<Unit>

    @PATCH("companies/{id}/tags")
    suspend fun updateCompanyTags(
        @Path("id") id: Long,
        @Body companyTags: CompanyTags
    ): Response<Unit>

    @PATCH("companies/{id}/businessAreas")
    suspend fun updateCompanyBusinessAreas(
        @Path("id") id: Long,
        @Body businessAreas: CompanyBusinessAreas
    ): Response<Unit>


}