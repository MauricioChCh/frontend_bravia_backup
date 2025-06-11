package com.example.bravia.domain.repository

import com.example.bravia.domain.model.Location

interface LocationRepository {
    suspend fun getAllBusinessLocations(username: String): Result<List<Location>>
}