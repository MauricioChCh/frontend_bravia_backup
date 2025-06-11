package com.example.bravia.domain.usecase

import com.example.bravia.data.remote.dto.InternshipDTO
import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.repository.InternshipRepository
import javax.inject.Inject

class GetAllBusinessInternshipUseCase @Inject constructor(
    private val repository: InternshipRepository
) {

    suspend operator fun invoke(username: String): Result<List<Internship>> {
        return repository.getAllBusinessInternships(username)
    }

}