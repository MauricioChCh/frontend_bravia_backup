package com.example.bravia.data.remote.serializer

import com.example.bravia.data.remote.dto.InternshipDTO
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.Long

class InternshipDeserializer : JsonDeserializer<InternshipDTO> {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // adaptá esto al formato real

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): InternshipDTO {
        val obj = json.asJsonObject

        fun getString(name: String): String {
            val element = obj.get(name)
            return if (element != null && !element.isJsonNull) element.asString else ""
        }

        fun getLong(name: String): Long {
            val element = obj.get(name)
            return if (element != null && !element.isJsonNull) element.asLong else 0L
        }

        fun getDouble(name: String): Double {
            val element = obj.get(name)
            return if (element != null && !element.isJsonNull) element.asDouble else 0.0
        }

        fun getDate(name: String): Date {
            val element = obj.get(name)
            return if (element != null && !element.isJsonNull) {
                try {
                    dateFormat.parse(element.asString) ?: Date()
                } catch (_: Exception) {
                    Date()
                }
            } else {
                Date()
            }
        }


        return InternshipDTO(
            id = getLong("id") ,
            title = getString("title"),
            companyName = getString("companyName"),
            cityName = getString("cityName"),
            countryName = getString("countryName"),
            modality = getString("modality"),
            schedule = getString("schedule"),
            requirements = getString("requirements"),
            activities = getString("activities"),
            link = getString("link"),
            publicationDate = getDate("publicationDate"),
            imageUrl = getString("imageUrl") ,
            duration = getString("duration"),
            salary = getDouble("salary"),
            locationFullName = getString("locationFullName"),
        )
    }
}
