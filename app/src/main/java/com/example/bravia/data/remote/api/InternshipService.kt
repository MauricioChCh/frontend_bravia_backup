package com.example.bravia.data.remote.api

import com.example.bravia.data.remote.dto.InternshipDTO
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.Response
import retrofit2.http.GET

interface InternshipService {

    @PATCH("internships/{internshipId}/users/{userId}/bookmark")
    suspend fun bookmarkInternship(
        @Path("internshipId") internshipId: Long,
        @Path("userId") userId: Long,
        @Body isBookmarked: Boolean
    ): Response<Unit>


    @GET("internships/users/{userId}/bookmarked")
    suspend fun getBookmarkedInternships(@Path("userId") userId: Long): Response<List<InternshipDTO>>
}