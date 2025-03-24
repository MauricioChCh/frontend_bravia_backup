package com.example.bravia.data.datasource

import com.example.bravia.data.model.Internship
import java.util.Date

class InternshipsProvider {
    companion object {
        private val internshipMainList = listOf(
            Internship(
                id = 1,
                title = "Data Science Intern",
                company = "Netflix",
                description = "Join our data science team to develop innovative machine learning models",
                imageUrl = "https://www.netflix.com",
                location = "Los Gatos, CA",
                publicationDate = Date(),
                duration = "4 months",
                salary = 1500.0,
                modality = "Hybrid",
                schedule = "Part time",
                requirements = "Python, Machine Learning, Statistics",
                percentage = "80%",
                activities = "Develop and analyze machine learning models, assist in data visualization",
                contact = "650-555-1234",
                link = "https://careers.netflix.com/internships"
            ),
            Internship(
                id = 2,
                title = "UX Design Intern",
                company = "Airbnb",
                description = "Create intuitive and engaging user experiences for our global platform",
                imageUrl = "https://www.airbnb.com",
                location = "San Francisco, CA",
                publicationDate = Date(),
                duration = "5 months",
                salary = 1200.0,
                modality = "Remote",
                schedule = "Full time",
                requirements = "Figma, Adobe XD, User Research",
                percentage = "90%",
                activities = "Design user interfaces, conduct user research, create wireframes and prototypes",
                contact = "415-555-6789",
                link = "https://www.airbnb.com/careers/internships"
            ),
            Internship(
                id = 3,
                title = "Cybersecurity Intern",
                company = "Microsoft",
                description = "Contribute to our cybersecurity team in protecting digital assets",
                imageUrl = "https://www.microsoft.com",
                location = "Redmond, WA",
                publicationDate = Date(),
                duration = "6 months",
                salary = 1800.0,
                modality = "On-site",
                schedule = "Full time",
                requirements = "Network Security, Ethical Hacking, Cybersecurity Fundamentals",
                percentage = "100%",
                activities = "Perform security assessments, assist in vulnerability testing, monitor security systems",
                contact = "425-555-9012",
                link = "https://careers.microsoft.com/cybersecurity-internship"
            ),
            Internship(
                id = 4,
                title = "Marketing Analytics Intern",
                company = "Spotify",
                description = "Help us leverage data to drive marketing strategies",
                imageUrl = "https://www.spotify.com",
                location = "New York, NY",
                publicationDate = Date(),
                duration = "3 months",
                salary = 1100.0,
                modality = "Hybrid",
                schedule = "Part time",
                requirements = "Excel, SQL, Google Analytics, Marketing Fundamentals",
                percentage = "75%",
                activities = "Analyze marketing campaign performance, create data reports, support marketing team",
                contact = "212-555-3456",
                link = "https://www.spotify.com/careers/marketing-internship"
            ),
            Internship(
                id = 5,
                title = "Software Engineering Intern",
                company = "Amazon Web Services",
                description = "Develop cloud computing solutions and contribute to innovative projects",
                imageUrl = "https://aws.amazon.com",
                location = "Seattle, WA",
                publicationDate = Date(),
                duration = "6 months",
                salary = 1600.0,
                modality = "On-site",
                schedule = "Full time",
                requirements = "Java, Cloud Computing, AWS Services, Kubernetes",
                percentage = "100%",
                activities = "Develop cloud applications, assist in infrastructure design, write technical documentation",
                contact = "206-555-7890",
                link = "https://aws.amazon.com/careers/internships"
            )
        )

        fun findInternshipById(id: Long): Internship? {
            return internshipMainList.find { it.id == id }
        }

        fun findAllInternships(): List<Internship> {
            return internshipMainList
        }
    }
}