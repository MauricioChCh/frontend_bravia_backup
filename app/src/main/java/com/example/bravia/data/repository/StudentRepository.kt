package com.example.bravia.data.repository

import com.example.bravia.data.mapper.StudentMapper
import com.example.bravia.data.remote.StudentRemoteDataSource
import com.example.bravia.domain.model.Student
import jakarta.inject.Inject

class StudentRepository @Inject constructor(
    private val remoteDataSource: StudentRemoteDataSource,
    private val mapper: StudentMapper
) {

     suspend fun getStudentByUsername(username: String): Result<Student?> {
        return try {
            remoteDataSource.getStudentByUsername(username).map { dto ->
                mapper.mapToDomain(dto!!)
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error fetching student: ${e.message}"))
        }
    }
}