package com.example.bravia.data.remote.serializer

import com.example.bravia.data.remote.dto.BusinessAreaDTO
import com.example.bravia.data.remote.dto.CompanyResponseDTO
import com.example.bravia.data.remote.dto.ContactDTO
import com.example.bravia.data.remote.dto.LocationDTO
import com.example.bravia.data.remote.dto.TagDTO
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CompanyDeserializer : JsonDeserializer<CompanyResponseDTO>    {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): CompanyResponseDTO {
        val jsonObject = json.asJsonObject

        val id = jsonObject.get("id").asLong
        val name = jsonObject.get("name").asString
        val description = jsonObject.get("description").asString
        val imageUrl = jsonObject.get("imageUrl").asString

        val businessAreas = jsonObject.get("businessAreas").asJsonArray.map {
            context.deserialize<BusinessAreaDTO>(it, BusinessAreaDTO::class.java)
        }

        val tags = jsonObject.get("tags").asJsonArray.map {
            context.deserialize<TagDTO>(it, TagDTO::class.java)
        }

        val location = context.deserialize<LocationDTO>(
            jsonObject.get("location"),
            LocationDTO::class.java
        )

        val contacts = jsonObject.get("contacts").asJsonArray.map {
            context.deserialize<ContactDTO>(it, ContactDTO::class.java)
        }

        val firstName = jsonObject.get("firstName").asString
        val lastName = jsonObject.get("lastName").asString
        val email = jsonObject.get("email").asString
        val verified = jsonObject.get("verified").asBoolean

        return CompanyResponseDTO(
            id = id,
            name = name,
            description = description,
            imageUrl = imageUrl,
            businessAreas = businessAreas,
            tags = tags,
            location = location,
            contacts = contacts,
            firstName = firstName,
            lastName = lastName,
            email = email,
            verified = verified
        )
    }
}