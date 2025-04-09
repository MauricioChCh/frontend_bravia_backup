package com.example.bravia.data.remote


import com.example.bravia.data.remote.api.BusinessService
import com.example.bravia.data.remote.api.StudentAreaService
import com.example.bravia.data.remote.dto.InternshipDTO
import com.example.bravia.data.remote.utils.ApiCallHandler
import retrofit2.Response
import javax.inject.Inject

class InternshipRemoteDataSource @Inject constructor(
    private val studentAreaService: StudentAreaService,
    private val businessService: BusinessService
) {
    suspend fun getRecommendedInternships(): Result<List<InternshipDTO>> = ApiCallHandler.safeApiCall {
        studentAreaService.getRecommendedInternships() as Response<List<InternshipDTO>>
    }

    suspend fun getInternshipById(id: Long): Result<InternshipDTO?> =
        ApiCallHandler.safeApiCall<InternshipDTO?>{
            studentAreaService.getInternshipById(id)
        }

    suspend fun getAllBusinessInternships(id: Long): Result<List<InternshipDTO>> =
        ApiCallHandler.safeApiCall {
            businessService.getAllBusinessInternships(id)
        }

    suspend fun getBusinessInternshipById(id: Long): Result<InternshipDTO?> =
        ApiCallHandler.safeApiCall<InternshipDTO?> {
            businessService.getBusinessInternshipById(id)
        }

}