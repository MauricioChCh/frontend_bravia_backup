package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Interest
import com.example.bravia.domain.repository.InterestRepository

class GetInterestByIdUseCase (private val repository: InterestRepository) {
    operator fun invoke(id: Long): Interest? {
        return repository.getInterestById(id)
    }
}