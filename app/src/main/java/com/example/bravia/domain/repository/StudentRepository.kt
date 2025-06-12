package com.example.bravia.domain.repository

import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.StudentProfile

interface StudentRepository {
    suspend fun getStudetByUserName(username: String): Result<StudentProfile?>
}