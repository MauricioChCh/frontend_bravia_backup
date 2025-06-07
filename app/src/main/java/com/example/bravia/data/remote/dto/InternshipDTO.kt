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
    val locationFullName: String,
)