package com.example.bravia.domain.usecase

import android.util.Log
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.Student
import com.example.bravia.domain.repository.AdminRepository
import javax.inject.Inject

class GetAllStudentsUseCase @Inject constructor(
    private val repository: AdminRepository
) {
    suspend operator fun invoke(): Result<List<Student>> {
        return try {
            val students = repository.findAllStudents()
            Log.d("GetAllStudentsUseCase", "Fetched students count: ${students.size}")
            students.forEach { student ->
                Log.d("GetAllStudentsUseCase", "student -> id: ${student.id}, name: ${student.userInput?.firstName} ${student.userInput?.lastName}, description: ${student.description}")
            }
            Result.success(students)
        } catch (e: Exception) {
            Log.e("GetAllStudentsUseCase", "Error fetching students", e)
            Result.failure(e)
        }
    }
}