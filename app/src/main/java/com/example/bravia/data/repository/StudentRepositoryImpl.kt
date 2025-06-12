package com.example.bravia.data.repository

import com.example.bravia.data.mapper.StudentMapper
import com.example.bravia.data.remote.StudentRemoteDataSource
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.Student
import com.example.bravia.domain.model.StudentProfile
import com.example.bravia.domain.repository.StudentRepository
import jakarta.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val remoteDataSource: StudentRemoteDataSource,
    private val mapper: StudentMapper
) : StudentRepository {


    override suspend fun getStudetByUserName(username: String): Result<StudentProfile> {
        return try {
            remoteDataSource.getStudentByUsername(username).map { dto ->
                mapper.mapToDomain(dto!!)
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error fetching student: ${e.message}"))
        }

    }
}