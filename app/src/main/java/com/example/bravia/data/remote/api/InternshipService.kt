package com.example.bravia.data.remote.api

import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.Response

interface InternshipService {

    @PATCH("internships/{internshipId}/users/{userId}/bookmark")
    suspend fun bookmarkInternship(
        @Path("internshipId") internshipId: Long,
        @Path("userId") userId: Long,
        @Body isBookmarked: Boolean
    ): Response<Unit>

}