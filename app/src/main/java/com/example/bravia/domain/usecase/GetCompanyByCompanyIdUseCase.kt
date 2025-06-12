package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Company
import com.example.bravia.domain.repository.CompanyRepository
import javax.inject.Inject

class GetCompanyByCompanyIdUseCase @Inject constructor(
    private val repository: CompanyRepository
){

    suspend operator fun invoke(id: Long): Result<Company?> {
        return repository.getCompanyByCompanyId(id)
    }
}