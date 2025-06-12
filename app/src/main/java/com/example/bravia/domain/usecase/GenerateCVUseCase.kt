package com.example.bravia.domain.usecase


import com.example.bravia.data.remote.dto.CVGenerationResponse
import com.example.bravia.data.remote.dto.CVRequestDTO
import com.example.bravia.domain.model.Student
import com.example.bravia.domain.model.StudentNew
import com.example.bravia.domain.model.StudentProfile
import com.example.bravia.domain.repository.AuthRepository
import com.example.bravia.domain.repository.CVRepository
import javax.inject.Inject

class GenerateCVUseCase @Inject constructor(
    private val cvRepository: CVRepository
) {
    suspend operator fun invoke(
        studentProfile: StudentProfile,
        additionalInfo: String
    ): CVGenerationResponse {
        return cvRepository.generateCV(
            CVRequestDTO(
                studentProfile = studentProfile,
                additionalInfo = additionalInfo,
                format = "PDF", // o "LATEX" si prefieres
                template = "MODERN" // Podrías tener varios templates
            )
        )
    }
}