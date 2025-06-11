package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.CompanyNew
import com.example.bravia.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterBusinessUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {


    /**
     * Invokes the use case to register a business.
     *
     * @return A [Result] indicating the success or failure of the registration.
     */
    suspend operator fun invoke(company: CompanyNew): Result<Company> {
        return authRepository.registerBusiness(company)
    }
}