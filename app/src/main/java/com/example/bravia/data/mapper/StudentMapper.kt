package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.StudentNewDTO
import com.example.bravia.data.remote.dto.StudentResponseAdminDTO
import com.example.bravia.data.remote.dto.StudentResponseDTO
import com.example.bravia.domain.model.Student
import com.example.bravia.domain.model.StudentNew
import com.example.bravia.data.remote.dto.*
import com.example.bravia.domain.model.*
import javax.inject.Inject

class StudentMapper @Inject constructor(
    private val collegeMapper: CollegeMapper,
    private val degreeMapper: DegreeMapper,
    private val interestMapper: InterestMapper,
    private val institutionMapper: InstitutionMapper,
    private val locationMapper: LocationMapper,
    private val hobbyMapper: HobbyMapper,
    private val certificationMapper: CertificationMapper,
    private val experienceMapper: ExperienceMapper,
    private val skillMapper: SkillMapper,
    private val careerMapper: CareerMapper,
    private val languageMapper: LanguageMapper,
    private val educationMapper: EducationMapper,
    private val contactMapper: ContactMapper
) {

    fun mapToDomain(dto: StudentResponseDTO): Student {
        return Student(
            id = dto.id,
            firstName = dto.firstName,
            lastName = dto.lastName,
            email = dto.email,
            description = null,
            academicCenter = null,
            userInput = null
        )
    }

    fun mapToDomain(dto: StudentResponseAdminDTO): Student {
        return Student(
            id = dto.id,
            description = dto.description,
            academicCenter = dto.academicCenter,
            userInput = dto.userInput,
            firstName = null,
            lastName = null,
            email = null
        )
    }

    fun mapToNewDTO(student: StudentNew): StudentNewDTO {
        return StudentNewDTO(
            email = student.email,
            password = student.password,
            confirmPassword = student.confirmPassword,
            firstName = student.firstName,
            lastName = student.lastName,
            college = collegeMapper.mapToDto(student.college),
            degree = degreeMapper.mapToDto(student.degree),
            interest = student.interest.map { interestMapper.mapToDto(it) },
        )
    }

    fun mapToDomain(dto: StudentResponseProfileDTO): StudentProfile {
        return StudentProfile(
            id = dto.id,
            firstName = dto.firstName,
            lastName = dto.lastName,
            email = dto.email,
            description = dto.description,
            academicCenter = dto.academicCenter,
            institution = dto.institution?.let { institutionMapper.mapToDomain(it) },
            location = dto.location?.let { locationMapper.mapToDomain(it) },
            hobbies = dto.hobbies?.map { hobbyMapper.mapToDomain(it) },
            certifications = dto.certifications?.map { certificationMapper.mapToDomain(it) },
            certificates = dto.certificates?.map {
                Certificate(
                    id = it.id,
                    name = it.name,
                    organization = it.organization,
                    startDate = it.startDate
                )
            },
            experiences = dto.experiences?.map { experienceMapper.mapToDomain(it) },
            skills = dto.skills?.map { skillMapper.mapToDomain(it) },
            careers = dto.careers?.map { careerMapper.mapToDomain(it) },
            cvUrls = dto.cvUrls,
            languages = dto.languages?.map { languageMapper.mapToDomain(it) },
            degrees = dto.degrees?.map { degreeMapper.mapToDomain(it) },
            colleges = dto.colleges?.map { collegeMapper.mapToDomain(it) },
            interests = dto.interests?.map { interestMapper.mapToDomain(it) },
            education = dto.education?.map { educationMapper.mapToDomain(it) },
            contacts = dto.contacts?.map { contactMapper.mapToDomain(it) }
        )
    }








}




