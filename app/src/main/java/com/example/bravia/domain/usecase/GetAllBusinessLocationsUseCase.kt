package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Location
import com.example.bravia.domain.repository.LocationRepository
import javax.inject.Inject

class GetAllBusinessLocationsUseCase @Inject constructor(
    private val repository: LocationRepository
) {

    suspend operator fun invoke(username: String): Result<List<Location>> {
        return repository.getAllBusinessLocations(username)
    }

}