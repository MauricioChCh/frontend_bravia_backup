package com.example.bravia.domain.repository

import com.example.bravia.domain.model.Location

interface LocationRepository {
    suspend fun getAllBusinessLocations(companyId: Long): Result<List<Location>>
}