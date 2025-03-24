package com.example.bravia.data.datasource

import com.example.bravia.data.model.Internship
import java.util.Date

class InternshipsProvider {
    companion object{
        private val intershipMainList = listOf(
            Internship(
                id = 1,
                title = "Software Developer",
                company = "Google",
                description = "We are looking for a software developer to join our team",
                imageUrl = "https://www.google.com",
                location = "SanJose",
                publicationDate = Date(),
                duration = "6 months",
                salary = 1000.0,
                modality = "Remote",
                schedule = "Full time",
                requirements = "Java, Kotlin, Android",
                percentage = "100%",
                activities = "Develop software",
                contact = "88338383",
                link = "https://www.google.com"
            ),
            Internship(
                id = 2,
                title = "Software Developer",
                company = "Google",
                description = "We are looking for a software developer to join our team",
                imageUrl = "https://www.google.com",
                location = "SanJose",
                publicationDate = Date(),
                duration = "6 months",
                salary = 1000.0,
                modality = "Remote",
                schedule = "Full time",
                requirements = "Java, Kotlin, Android",
                percentage = "100%",
                activities = "Develop software",
                contact = "88338383",
                link = "https://www.google.com"
            ),
            Internship(
                id = 3,
                title = "Software Developer",
                company = "Google",
                description = "We are looking for a software developer to join our team",
                imageUrl = "https://www.google.com",
                location = "SanJose",
                publicationDate = Date(),
                duration = "6 months",
                salary = 1000.0,
                modality = "Remote",
                schedule = "Full time",
                requirements = "Java, Kotlin, Android",
                percentage = "100%",
                activities = "Develop software",
                contact = "88338383",
                link = "https://www.google.com"
            ),
        )

        fun findInternshipById(id: Long): Internship? {
            return intershipMainList.find { it.id == id }
        }

        fun findAllInternships(): List<Internship> {
            return intershipMainList
        }

    }
}