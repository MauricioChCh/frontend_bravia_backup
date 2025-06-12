package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.City
import com.example.bravia.domain.repository.LocationRepository
import javax.inject.Inject

class GetAllCitiesUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    /**
     * Retrieves all cities from the repository.
     *
     * @return A [Result] containing a list of cities or an error.
     */
    suspend operator fun invoke(): Result<List<City>> {
        return repository.getAllCities()
    }
}