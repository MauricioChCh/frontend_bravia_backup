package com.example.bravia.domain.usecase


import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.repository.InternshipRepository

class GetInternshipByIdUseCase(private val repository: InternshipRepository) {
    operator fun invoke(id: Long): Internship? {
        return repository.getInternshipById(id)
    }
}