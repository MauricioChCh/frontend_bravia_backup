package com.example.bravia.domain.model

data class Student(
    val id: Long?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
)


data class StudentNew(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val firstName: String,
    val lastName: String,
    val college: College,
    val degree: Degree,
    val interest: List<Interest>,
)

// ==================== ENTITIES ====================

data class StudentProfile(
    val id: Long?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    // Campos adicionales para el perfil
    val description: String? = null,
    val academicCenter: String? = null, // Esto viene del backend
    val institution: Institution? = null,
    val location: Location? = null,
    val hobbies: List<Hobby>? = null,
    val certifications: List<Certification>? = null,
    val certificates: List<Certificate>? = null,
    val experiences: List<Experience>? = null,
    val skills: List<Skill>? = null,
    val careers: List<Career>? = null,
    val cvUrls: List<String>? = null,
    val languages: List<Language>? = null,
    val degrees: List<Degree>? = null,
    val colleges: List<College>? = null,
    val interests: List<Interest>? = null,
    val education: List<Education>? = null,
    val contacts: List<Contact>? = null
)



// ==================== SUPPORTING ENTITIES ====================

data class Institution(
    val id: Long?,
    val name: String?
)

data class Education(
    val id: Long?,
    val institution: Institution?,
    val degree: String?,
    val startDate: String?,
    val endDate: String? = null
)


data class Hobby(
    val id: Long?,
    val name: String
)

data class Certification(
    val id: Long?,
    val name: String,
    val date: String?, // Usando String para fechas, puedes cambiar a Date si prefieres
    val organization: String
)

// Para compatibilidad con ProfileScreen
data class Certificate(
    val id: Long?,
    val name: String?,
    val organization: String?,
    val startDate: String?
)

data class Experience(
    val id: Long?,
    val name: String,
    val description: String
)

data class Skill(
    val id: Long?,
    val name: String,
    val description: String
)

data class Career(
    val id: Long?,
    val career: String
)

data class Language(
    val id: Long?,
    val name: String,
    val level: String? = null
)




// ==================== MAPPERS ====================

//// Mapper functions para convertir entre Entity y DTO
//fun Student.toResponseDTO(): StudentResponseDTO {
//    return StudentResponseDTO(
//        id = this.id ?: 0,
//        firstName = this.firstName,
//        lastName = this.lastName,
//        email = this.email,
//        description = this.description,
//        academicCenter = this.academicCenter,
//        institution = this.institution?.toDTO(),
//        location = this.location?.toDTO(),
//        hobbies = this.hobbies?.map { it.toDTO() },
//        certifications = this.certifications?.map { it.toDTO() },
//        certificates = this.certificates?.map { it.toDTO() },
//        experiences = this.experiences?.map { it.toDTO() },
//        skills = this.skills?.map { it.toDTO() },
//        careers = this.careers?.map { it.toDTO() },
//        cvUrls = this.cvUrls,
//        languages = this.languages?.map { it.toDTO() },
//        degrees = this.degrees?.map { it.toDTO() },
//        colleges = this.colleges?.map { it.toDTO() },
//        interests = this.interests?.map { it.toDTO() },
//        education = this.education?.map { it.toDTO() },
//        contacts = this.contacts?.map { it.toDTO() }
//    )
//}
//
//fun StudentNewDTO.toEntity(): StudentNew {
//    return StudentNew(
//        email = this.email,
//        password = this.password,
//        confirmPassword = this.confirmPassword,
//        firstName = this.firstName,
//        lastName = this.lastName,
//        college = this.college.toEntity(),
//        degree = this.degree.toEntity(),
//        interest = this.interest.map { it.toEntity() }
//    )
//}
//
//// Extension functions para mappers individuales
//fun Institution.toDTO() = InstitutionDTO(id, name)
//fun Location.toDTO() = LocationDTO(id, city?.toDTO(), country?.toDTO())
//fun City.toDTO() = CityDTO(id, name)
//fun Country.toDTO() = CountryDTO(id, name)
//fun Hobby.toDTO() = HobbyDTO(id, name)
//fun Certification.toDTO() = CertificationDTO(id, name, date, organization)
//fun Certificate.toDTO() = CertificateDTO(id, name, organization, startDate)
//fun Experience.toDTO() = ExperienceDTO(id, name, description)
//fun Skill.toDTO() = SkillDTO(id, name, description)
//fun Career.toDTO() = CareerDTO(id, career)
//fun Language.toDTO() = LanguageDTO(id, name, level)
//fun Degree.toDTO() = DegreeDTO(id, name, level)
//fun College.toDTO() = CollegeDTO(id, name, location)
//fun Interest.toDTO() = InterestDTO(id, name)
//fun Education.toDTO() = EducationDTO(id, institution?.toDTO(), degree, startDate, endDate)
//fun Contact.toDTO() = ContactDTO(id, type, url)
//
//// Reverse mappers
//fun CollegeDTO.toEntity() = College(id, name, location)
//fun DegreeDTO.toEntity() = Degree(id, name, level)
//fun InterestDTO.toEntity() = Interest(id, name)