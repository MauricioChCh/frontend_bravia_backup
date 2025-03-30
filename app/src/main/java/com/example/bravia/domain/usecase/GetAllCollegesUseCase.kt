package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.College
import com.example.bravia.domain.repository.CollegeRepository

class GetAllCollegesUseCase (
    private val repository: CollegeRepository
) {
    operator fun invoke(): List<College> {
        return repository.getAllColleges()
    }
}