package com.example.bravia.data.remote.dto


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
)