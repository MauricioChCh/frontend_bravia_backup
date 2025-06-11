package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Student
import com.example.bravia.domain.model.StudentNew
import com.example.bravia.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterStudentUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * Invokes the use case to register a student.
     *
     * @param input The input data required for student registration.
     * @return A [Result] indicating the success or failure of the registration.
     */
    suspend operator fun invoke(student: StudentNew): Result<Student> {
        return authRepository.registerStudent(student)
    }
}