package com.example.bravia.domain.model

import java.util.Date


data class Company(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val businessAreas: List<BusinessArea>,
    val tags: List<Tag>,
    val location: Location,
    val contacts: List<Contact>,
    val firstName: String,
    val lastName: String,
    val email: String,
    val verified: Boolean,
)

data class CompanyNew(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val name: String,
    val businessArea: BusinessArea,
)
