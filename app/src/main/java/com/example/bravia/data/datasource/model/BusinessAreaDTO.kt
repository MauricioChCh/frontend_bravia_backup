package com.example.bravia.data.datasource.model

/**
 * Data Transfer Object (DTO) representing a business area.
 *
 * @property id The unique identifier of the business area.
 * @property name The name of the business area.
 */
data class BusinessAreaDTO(
    val id: Long,
    val name: String
)