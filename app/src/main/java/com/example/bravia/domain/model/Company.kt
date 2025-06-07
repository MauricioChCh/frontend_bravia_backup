package com.example.bravia.domain.model

import java.util.Date


data class Company(
    val id: Long,
    val name: String,
    val lastName: String,
    val email: String,
    val createDate: Date,
    val companyName: String,
    val description: String,
    val imageUrl: String,
    val businessArea: List<BusinessArea>,
    val tags: List<Tag>,
    val location: Location,
    val contacts: List<Contact>,
)
