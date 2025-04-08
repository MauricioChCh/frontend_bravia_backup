package com.example.bravia.data.remote.dto

import java.util.Date

data class InternshipDTO(
    val id: Long,
    val title: String,
    val company: String,
    val description: String,
    val imageUrl: String,
    val location: String,
    val publicationDate: Date,
    val duration: String,
    val salary: Double,
    val modality: String,
    val schedule: String,
    val requirements: String,
    val percentage: String,
    val activities: String,
    val contact: String,
    val link: String
)