package com.example.bravia.data.remote


import com.example.bravia.data.remote.api.BusinessService
import com.example.bravia.data.remote.api.InternshipService
import com.example.bravia.data.remote.api.StudentAreaService
import com.example.bravia.data.remote.dto.InternshipDTO
import com.example.bravia.data.remote.dto.NewInternshipDTO
import com.example.bravia.data.remote.utils.ApiCallHandler
import retrofit2.Response
import javax.inject.Inject

class InternshipRemoteDataSource @Inject constructor(
    private val studentAreaService: StudentAreaService,
    private val businessService: BusinessService,
    private val internshipService: InternshipService,
) {
    suspend fun getRecommendedInternships(): Result<List<InternshipDTO>> =
        ApiCallHandler.safeApiCall {
        studentAreaService.getRecommendedInternships() as Response<List<InternshipDTO>>
    }

    suspend fun getInternshipById(id: Long): Result<InternshipDTO?> =
        ApiCallHandler.safeApiCall<InternshipDTO?> {
            println("Fetching internship with id: $id")
            val response = studentAreaService.getInternshipById(id)
            println("Response for internship $id: ${response.body()}")
            response
        }

    suspend fun getAllBusinessInternships(username: String): Result<List<InternshipDTO>> =
        ApiCallHandler.safeApiCall {
            businessService.getAllBusinessInternships(username)
        }

    suspend fun getBusinessInternshipById(username: String, internshipId: Long): Result<InternshipDTO?> =
        ApiCallHandler.safeApiCall<InternshipDTO?> {
            businessService.getBusinessInternshipById(username, internshipId)
        }

    suspend fun newInternship(internship: NewInternshipDTO): Result<InternshipDTO?> =
        ApiCallHandler.safeApiCall<InternshipDTO?> {
            businessService.newInternship(internship)
        }

    suspend fun bookmarkInternship(internshipId: Long, username: String, isBookmarked: Boolean): Result<Unit> =
        ApiCallHandler.safeApiCall<Unit> {
            internshipService.bookmarkInternship(internshipId, username, isBookmarked)
        }

    suspend fun getBookmarkedInternships(username: String): Result<List<InternshipDTO>> =
        ApiCallHandler.safeApiCall {
            internshipService.getBookmarkedInternships(username)
        }
}