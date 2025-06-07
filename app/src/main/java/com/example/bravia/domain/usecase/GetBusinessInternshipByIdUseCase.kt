package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.repository.InternshipRepository
import javax.inject.Inject

class GetBusinessInternshipByIdUseCase @Inject constructor(
    private val repository: InternshipRepository
) {


    operator suspend fun invoke(businessId: Long, internshipId: Long): Result<Internship?> {
        return repository.getBusinessInternshipById(businessId, internshipId)
    }
}