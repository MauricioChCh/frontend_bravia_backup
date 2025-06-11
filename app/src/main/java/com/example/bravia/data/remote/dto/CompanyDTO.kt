package com.example.bravia.data.remote.dto

data class CompanyResponseDTO(
    var id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    var imageUrl: String? = null,
    var businessAreas: List<BusinessAreaDTO>? = null,
    var tags: List<TagDTO>? = null,
    var location: LocationDTO? = null,
    var contacts: List<ContactDTO>? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var verified: Boolean? = null,
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