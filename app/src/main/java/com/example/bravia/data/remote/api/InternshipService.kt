package com.example.bravia.data.remote.api

import com.example.bravia.data.remote.dto.InternshipDTO
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.Response
import retrofit2.http.GET

interface InternshipService {

    @PATCH("internships/{internshipId}/users/{username}/bookmark")
    suspend fun bookmarkInternship(
        @Path("internshipId") internshipId: Long,
        @Path("username") username: String,
        @Body isBookmarked: Boolean
    ): Response<Unit>


    @GET("internships/users/{username}/bookmarked")
    suspend fun getBookmarkedInternships(@Path("username") username: String): Response<List<InternshipDTO>>
}