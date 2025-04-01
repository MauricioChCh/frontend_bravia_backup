package com.example.bravia.data.remote.dto

/**
 * Data Transfer Object (DTO) representing a college.
 *
 * @property id The unique identifier of the college.
 * @property name The name of the college.
 */
data class CollegeDTO(
    val id: Long,
    val name: String
)