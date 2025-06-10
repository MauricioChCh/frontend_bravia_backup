package com.example.bravia.domain.model


/**
 * Data class representing an interest.
 *
 * @property id The unique identifier of the interest.
 * @property name The name of the interest.
 * @property category The category of the interest.
 */
data class Interest (
    val id: Long,
    val name: String
)