package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.BusinessArea
import com.example.bravia.domain.repository.BusinessAreaRepository

class GetAllBusinessAreaUseCase (
    private val repository: BusinessAreaRepository
) {
    operator fun invoke(): List<BusinessArea> {
        return repository.getAllBusinessAreas()
    }
}