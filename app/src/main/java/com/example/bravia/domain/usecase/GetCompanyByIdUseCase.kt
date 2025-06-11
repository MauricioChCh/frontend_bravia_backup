package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Company
import com.example.bravia.domain.repository.CompanyRepository
import javax.inject.Inject

class GetCompanyByIdUseCase @Inject constructor(
    private val repository: CompanyRepository
){

    suspend operator fun invoke(username: String): Result<Company?> {
        return repository.getCompanyById(username)
    }
}