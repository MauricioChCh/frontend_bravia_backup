package com.example.bravia.data.remote.serializer

import com.example.bravia.data.remote.dto.DegreeDTO
import com.google.gson.*
import java.lang.reflect.Type

/**
 * Custom JSON deserializer for [DegreeDTO] objects.
 *
 * Handles special cases like:
 * - Mixed id formats (string or numeric)
 * - Date parsing
 * - Properly deserializing nested objects
 */
class DegreeDeserializer : JsonDeserializer<DegreeDTO> {
    /**
     * Deserializes a JSON element into a [DegreeDTO] object.
     *
     * @param json The JSON element to deserialize.
     * @param typeOfT The type of the object to deserialize into.
     * @param context The deserialization context.
     * @return The deserialized DegreeDTO object.
     * @throws JsonParseException if the JSON is not in the expected format.
     */
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): DegreeDTO {
        val jsonObject = json.asJsonObject

        val id = try {
            jsonObject.get("id").asLong
        } catch (e: NumberFormatException) {
            jsonObject.get("id").asString.toLong()
        }

        val name = jsonObject.get("name").asString

        return DegreeDTO(id, name)
    }
}