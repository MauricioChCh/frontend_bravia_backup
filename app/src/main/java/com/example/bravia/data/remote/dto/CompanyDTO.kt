package com.example.bravia.data.remote.dto

data class CompanyResponseDTO(
    var id: Long,
    var name: String,
    var description: String,
    var imageUrl: String,
    var businessAreas: List<BusinessAreaDTO>,
    var tags: List<TagDTO>,
    var location: LocationDTO,
    var contacts: List<ContactDTO>,
    var firstName: String,
    var lastName: String,
    var email: String,
    var verified: Boolean,
)

data class TagDTO(
    val id: Long,
    val name: String
)

data class CompanyNewDTO(
    var user: UserDTO,
    var name: String,
    var businessArea: BusinessAreaDTO,
)

data class UserDTO(
    var email: String,
    var password: String,
    var firstName: String,
    var lastName: String,
)