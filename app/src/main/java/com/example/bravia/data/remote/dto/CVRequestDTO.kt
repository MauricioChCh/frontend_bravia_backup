package com.example.bravia.data.remote.dto

import com.example.bravia.domain.model.StudentProfile

data class CVRequestDTO(
    val studentProfile: StudentProfile,
    val additionalInfo: String,
    val format: String,
    val template: String
)
data class CVGenerationResponse(
    val pdfUrl: String,
    val latexSource: String? = null // Opcional si quieres devolver también el código LaTeX (Mejor no)
)