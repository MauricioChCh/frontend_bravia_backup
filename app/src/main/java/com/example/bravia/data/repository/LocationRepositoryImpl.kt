package com.example.bravia.data.repository

import com.example.bravia.data.mapper.CityMapper
import com.example.bravia.data.mapper.CountryMapper
import com.example.bravia.data.mapper.LocationMapper
import com.example.bravia.data.remote.LocationRemoteDataSource
import com.example.bravia.domain.model.City
import com.example.bravia.domain.model.Country
import com.example.bravia.domain.model.Location
import com.example.bravia.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val remoteDataSource: LocationRemoteDataSource,
    private val locationMapper: LocationMapper,
    private val cityMapper: CityMapper,
    private val countryMapper: CountryMapper
) : LocationRepository {

    /**
     * Fetches all business locations from the remote data source.
     *
     * @return A [Result] containing a list of [Location] objects or an error.
     */
    override suspend fun getAllBusinessLocations(username: String): Result<List<Location>> {
        return try {
            val response = remoteDataSource.getAllBusinessLocations(username)
            if (response.isSuccess) {
                response.getOrNull()?.map { dto ->
                    locationMapper.mapToDomain(dto)
                }?.let { locations ->
                    Result.success(locations)
                } ?: Result.success(emptyList())
            } else {
                Result.failure(response.exceptionOrNull() ?: Exception("Unknown error fetching locations"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        } as Result<List<Location>>
    }

    override suspend fun getAllCities(): Result<List<City>> {
        return try {
            val response = remoteDataSource.getAllCities()
            if (response.isSuccess) {
                response.getOrNull()?.map { dto ->
                    cityMapper.mapToDomain(dto)
                }?.let { cities ->
                    Result.success(cities)
                } ?: Result.success(emptyList())
            } else {
                Result.failure(response.exceptionOrNull() ?: Exception("Unknown error fetching cities"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAllCountries(): Result<List<Country>> {
        return try {
            val response = remoteDataSource.getAllCountries()
            if (response.isSuccess) {
                response.getOrNull()?.map { dto ->
                    countryMapper.mapToDomain(dto)
                }?.let { countries ->
                    Result.success(countries)
                } ?: Result.success(emptyList())
            } else {
                Result.failure(response.exceptionOrNull() ?: Exception("Unknown error fetching countries"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}