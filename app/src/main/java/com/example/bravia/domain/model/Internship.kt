package com.example.bravia.domain.model

import java.util.Date

data class Internship(
    val id: Long,
    val company: String,
    val city: String,
    val country: String,
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
    val isMarked: Boolean = false,
)