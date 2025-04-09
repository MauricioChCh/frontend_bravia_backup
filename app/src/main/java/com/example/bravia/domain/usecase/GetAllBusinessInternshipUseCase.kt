package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.repository.InternshipRepository
import javax.inject.Inject

class GetAllBusinessInternshipUseCase @Inject constructor(
    private val repository: InternshipRepository
) {


    operator suspend fun invoke(id: Long): Result<List<Internship>> {
        return repository.getAllBusinessInternships(id)
    }

}