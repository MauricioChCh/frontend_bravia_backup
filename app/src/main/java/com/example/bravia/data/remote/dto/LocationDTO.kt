package com.example.bravia.data.remote.dto

import com.example.bravia.domain.model.Country

data class LocationDTO (
    val id: Long,
    val address: String,
    val city: CityDTO,
    val country: CountryDTO,
)

data class CityDTO(
    val id: Long,
    val name: String,
)

data class CountryDTO(
    val id: Long,
    val name: String,
)