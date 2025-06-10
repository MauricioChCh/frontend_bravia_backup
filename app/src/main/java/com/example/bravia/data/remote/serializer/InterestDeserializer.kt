package com.example.bravia.data.remote.serializer

import com.google.gson.*
import com.example.bravia.data.remote.dto.InterestDTO
import java.lang.reflect.Type


class InterestDeserializer : JsonDeserializer<InterestDTO> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): InterestDTO {
        val jsonObject = json.asJsonObject

        val id = try {
            jsonObject.get("id").asLong
        } catch (e: NumberFormatException) {
            jsonObject.get("id").asString.toLong()
        }

        val name = jsonObject.get("name").asString

        return InterestDTO(id, name)
    }
}