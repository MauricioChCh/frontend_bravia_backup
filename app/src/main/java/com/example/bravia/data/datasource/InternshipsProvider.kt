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
            ),
            Internship(
                id = 6,
                title = "Mobile App Development Intern",
                company = "Facebook",
                description = "Work on developing and enhancing our mobile applications",
                imageUrl = "https://www.facebook.com",
                location = "Menlo Park, CA",
                publicationDate = Date(),
                duration = "6 months",
                salary = 1700.0,
                modality = "On-site",
                schedule = "Full time",
                requirements = "Kotlin, Android Studio, Mobile App Development",
                percentage = "85%",
                activities = "Develop new features, fix bugs, collaborate with the design team",
                contact = "650-555-2345",
                link = "https://www.facebook.com/careers/internships"
            ),
            Internship(
                id = 7,
                title = "Data Analyst Intern",
                company = "Google",
                description = "Analyze large datasets to help drive business decisions",
                imageUrl = "https://www.google.com",
                location = "Mountain View, CA",
                publicationDate = Date(),
                duration = "4 months",
                salary = 1400.0,
                modality = "Hybrid",
                schedule = "Part time",
                requirements = "SQL, Python, Data Analysis",
                percentage = "90%",
                activities = "Analyze data, create reports, support data-driven decision making",
                contact = "650-555-6789",
                link = "https://careers.google.com/internships"
            ),
            Internship(
                id = 8,
                title = "Product Management Intern",
                company = "Apple",
                description = "Assist in managing product development and strategy",
                imageUrl = "https://www.apple.com",
                location = "Cupertino, CA",
                publicationDate = Date(),
                duration = "5 months",
                salary = 1600.0,
                modality = "Remote",
                schedule = "Full time",
                requirements = "Product Management, Agile, Communication Skills",
                percentage = "95%",
                activities = "Coordinate with teams, manage product lifecycle, conduct market research",
                contact = "408-555-1234",
                link = "https://www.apple.com/careers/internships"
            ),
            Internship(
                id = 9,
                title = "Graphic Design Intern",
                company = "Adobe",
                description = "Create visual content for various digital platforms",
                imageUrl = "https://www.adobe.com",
                location = "San Jose, CA",
                publicationDate = Date(),
                duration = "3 months",
                salary = 1300.0,
                modality = "On-site",
                schedule = "Part time",
                requirements = "Adobe Creative Suite, Graphic Design, Creativity",
                percentage = "80%",
                activities = "Design graphics, collaborate with marketing team, create visual content",
                contact = "408-555-5678",
                link = "https://www.adobe.com/careers/internships"
            ),
            Internship(
                id = 10,
                title = "Business Development Intern",
                company = "Tesla",
                description = "Support business development initiatives and strategies",
                imageUrl = "https://www.tesla.com",
                location = "Palo Alto, CA",
                publicationDate = Date(),
                duration = "6 months",
                salary = 1500.0,
                modality = "Hybrid",
                schedule = "Full time",
                requirements = "Business Development, Market Research, Communication Skills",
                percentage = "85%",
                activities = "Identify business opportunities, conduct market analysis, support sales team",
                contact = "650-555-7890",
                link = "https://www.tesla.com/careers/internships"
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