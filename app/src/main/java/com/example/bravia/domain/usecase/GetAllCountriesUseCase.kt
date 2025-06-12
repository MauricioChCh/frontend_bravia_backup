package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Country
import com.example.bravia.domain.repository.LocationRepository
import javax.inject.Inject

class GetAllCountriesUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    /**
     * Retrieves all countries from the repository.
     *
     * @return A [Result] containing a list of countries or an error.
     */
    suspend operator fun invoke(): Result<List<Country>> {
        return repository.getAllCountries()
    }
}