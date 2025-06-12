package com.example.bravia.data.remote

import com.example.bravia.data.remote.api.StudentService
import com.example.bravia.data.remote.dto.StudentNewDTO
import com.example.bravia.data.remote.dto.StudentResponseDTO
import com.example.bravia.data.remote.dto.StudentResponseProfileDTO
import jakarta.inject.Inject

class StudentRemoteDataSource  @Inject constructor(
    private val apiService: StudentService
) {

    /**
     * Retrieves a student by their ID.
     *
     * @param id The ID of the student to retrieve.
     * @return A [Result] containing the student data or an error.
     */
    suspend fun getStudentByUsername(id: String): Result<StudentResponseProfileDTO?> {
        return try {
            val response = apiService.getStudentByUsername(id)
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception("Error fetching student: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error fetching student: ${e.message}"))
        }
    }
}