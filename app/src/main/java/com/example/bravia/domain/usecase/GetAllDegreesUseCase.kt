package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Degree
import com.example.bravia.domain.repository.DegreeRepository

class GetAllDegreesUseCase (
    private val repository: DegreeRepository
) {
    operator fun invoke(): List<Degree> {
        return repository.getAllDegrees()
    }
}