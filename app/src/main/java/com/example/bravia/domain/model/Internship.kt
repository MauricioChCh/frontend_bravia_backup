package com.example.bravia.domain.model

import java.util.Date

data class Internship(
    val id: Long,
    val company: String,
    var city: String,
    var country: String,
    var title: String,
    var description: String,
    val imageUrl: String,
    val publicationDate: Date,
    var duration: String,
    var salary: Double,
    var modality: String,
    var schedule: String,
    var requirements: String,
    var activities: String,
    var link: String,
    val isMarked: Boolean,
)

data class NewInternship(
    val company: Long,
    val location: Long,
    val title: String,
    val description: String,
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