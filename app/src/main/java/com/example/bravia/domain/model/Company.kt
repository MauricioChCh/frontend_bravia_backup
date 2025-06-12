package com.example.bravia.domain.model


data class Company(
    val id: Long?,
    var name: String?,
    var description: String?,
    val imageUrl: String?,
    val businessAreas: List<BusinessArea>?,
    val tags: List<Tag>?,
    val location: Location?,
    val contacts: List<Contact>?,
    var firstName: String?,
    var lastName: String?,
    var email: String?,
    val verified: Boolean?,
)

data class CompanyNew(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val name: String,
    val businessArea: BusinessArea,
)
