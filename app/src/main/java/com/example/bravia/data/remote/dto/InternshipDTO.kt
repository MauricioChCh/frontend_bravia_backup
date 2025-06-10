package com.example.bravia.data.remote.dto

import java.util.Date

data class InternshipDTO(
    val id: Long,
    val title: String,
    val companyName: String,
    val cityName: String,
    val countryName: String,
    val modality: String,
    val schedule: String,
    val requirements: String,
    val activities: String,
    val link: String,
    val publicationDate: Date,
    val imageUrl: String,
    val duration: String,
    val salary: Double,
    val isBookmarked: Boolean,
    val locationFullName: String?,
)

data class NewInternshipDTO(
    val companyId: Long,
    val locationId: Long,
    val title: String,
    val imageUrl: String,
    val publicationDate: Date,
    val duration: String,
    val salary: Double,
    val modality: String,
    val schedule: String,
    val requirements: String,
    val activities: String,
    val link: String,
)