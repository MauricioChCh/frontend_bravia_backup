package com.example.bravia.domain.model

data class Location(
    val id: Long,
    val address: String,
    val city: City,
    val country: Country,
)
