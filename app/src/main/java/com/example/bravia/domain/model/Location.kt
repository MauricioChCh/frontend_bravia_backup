package com.example.bravia.domain.model

data class Location(
    val id: Long,
    val address: String,
    var city: City,
    var country: Country,
) {
    fun toShortString(): String {
        return "$address, ${city.name}, ${country.name}"
    }
}
