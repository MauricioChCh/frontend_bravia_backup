package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Interest
import com.example.bravia.domain.repository.InterestRepository

class GetAllInterestUseCase (private val repository: InterestRepository) {
    operator fun invoke(): List<Interest> {
        return repository.getAllInterests()
    }
}