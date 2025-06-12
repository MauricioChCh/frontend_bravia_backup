package com.example.bravia.domain.repository

import com.example.bravia.domain.model.City
import com.example.bravia.domain.model.Country
import com.example.bravia.domain.model.Location

interface LocationRepository {
    suspend fun getAllBusinessLocations(username: String): Result<List<Location>>
    suspend fun getAllCities(): Result<List<City>>
    suspend fun getAllCountries(): Result<List<Country>>
}