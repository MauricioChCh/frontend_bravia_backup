package com.example.bravia.data.datasource.model


/**
 * Data Transfer Object (DTO) representing an interest.
 *
 * @property id The unique identifier of the interest.
 * @property name The name of the interest.
 * @property category The category of the interest.
 */
data class InterestDTO(
    val id: Long,
    val name: String,
    val category: String
)