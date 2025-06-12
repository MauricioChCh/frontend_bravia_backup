package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.StudentProfile
import com.example.bravia.domain.repository.CompanyRepository
import com.example.bravia.domain.repository.StudentRepository
import javax.inject.Inject

class GetStudentByUsernameUseCase @Inject constructor(
    private val repository: StudentRepository
){

    suspend operator fun invoke(username: String): Result<StudentProfile?> {
        return repository.getStudetByUserName(username)
    }
}