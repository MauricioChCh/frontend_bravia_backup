package com.example.bravia.domain.usecase

import android.util.Log
import com.example.bravia.domain.model.Student
import com.example.bravia.domain.repository.AdminRepository
import javax.inject.Inject

class GetAdminStudentByIdUseCase @Inject constructor(
    private val repository: AdminRepository
) {
    suspend operator fun invoke(studentId: Long): Result<Student> {
        return try {
            val student = repository.findStudentById(studentId)
            Log.d(
                "GetAdminStudentByIdUseCase",
                "Fetched student id: ${student.id}, name: ${student.userInput?.firstName} ${student.userInput?.lastName}"
            )
            Result.success(student)
        } catch (e: Exception) {
            Log.e(
                "GetAdminStudentByIdUseCase",
                "Error fetching student with id $studentId",
                e
            )
            Result.failure(e)
        }
    }
}