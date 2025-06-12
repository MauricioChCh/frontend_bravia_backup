package com.example.bravia.data.remote.dto


data class StudentResponseProfileDTO(
    val id: Long?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val description: String? = null,
    val academicCenter: String? = null,
    val institution: InstitutionDTO? = null,
    val location: LocationDTO? = null,
    val hobbies: List<HobbyDTO>? = null,
    val certifications: List<CertificationDTO>? = null,
    val certificates: List<CertificateDTO>? = null,
    val experiences: List<ExperienceDTO>? = null,
    val skills: List<SkillDTO>? = null,
    val careers: List<CareerDTO>? = null,
    val cvUrls: List<String>? = null,
    val languages: List<LanguageDTO>? = null,
    val degrees: List<DegreeDTO>? = null,
    val colleges: List<CollegeDTO>? = null,
    val interests: List<InterestDTO>? = null,
    val education: List<EducationDTO>? = null,
    val contacts: List<ContactDTO>? = null
)



// ==================== SUPPORTING DTOs ====================

data class InstitutionDTO(
    val id: Long?,
    val name: String?
)

//data class CityDTO(
//    val id: Long?,
//    val name: String?
//)

//data class CountryDTO(
//    val id: Long?,
//    val name: String?
//)

data class HobbyDTO(
    val id: Long?,
    val name: String
)

data class CertificationDTO(
    val id: Long?,
    val name: String,
    val date: String?,
    val organization: String
)

data class CertificateDTO(
    val id: Long?,
    val name: String?,
    val organization: String?,
    val startDate: String?
)

data class ExperienceDTO(
    val id: Long?,
    val name: String,
    val description: String
)

data class SkillDTO(
    val id: Long?,
    val name: String,
    val description: String
)

data class CareerDTO(
    val id: Long?,
    val career: String
)

data class LanguageDTO(
    val id: Long?,
    val name: String,
    val level: String? = null
)


data class EducationDTO(
    val id: Long?,
    val institution: InstitutionDTO?,
    val degree: String?,
    val startDate: String?,
    val endDate: String? = null
)

