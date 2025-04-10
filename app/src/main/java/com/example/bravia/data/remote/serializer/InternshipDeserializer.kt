package com.example.bravia.data.remote.serializer

import com.example.bravia.data.remote.dto.InternshipDTO
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class InternshipDeserializer : JsonDeserializer<InternshipDTO> {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // adaptá esto al formato real

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): InternshipDTO {
        val obj = json.asJsonObject

        fun getString(name: String): String = obj.get(name)?.asString ?: ""
        fun getLong(name: String): Long = try { obj.get(name)?.asLong ?: 0L } catch (_: Exception) { 0L }
        fun getDouble(name: String): Double = try { obj.get(name)?.asDouble ?: 0.0 } catch (_: Exception) { 0.0 }
        fun getDate(name: String): Date = try { dateFormat.parse(obj.get(name)?.asString ?: "") ?: Date() } catch (_: Exception) { Date() }

        return InternshipDTO(
            id = getLong("id"),
            companyId = getLong("companyId"),
            title = getString("title"),
            company = getString("company"),
            description = getString("description"),
            imageUrl = getString("imageUrl"),
            location = getString("location"),
            publicationDate = getDate("publicationDate"),
            duration = getString("duration"),
            salary = getDouble("salary"),
            modality = getString("modality"),
            schedule = getString("schedule"),
            requirements = getString("requirements"),
            percentage = getString("percentage"),
            activities = getString("activities"),
            contact = getString("contact"),
            link = getString("link"),
            isMarked = try { obj.get("isMarked")?.asBoolean ?: false } catch (_: Exception) { false }
        )
    }
}
