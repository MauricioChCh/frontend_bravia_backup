package com.example.bravia.domain.repository

import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.Student
import com.example.bravia.domain.model.UserReport

interface AdminRepository {

    suspend fun findAllCompanies(): List<Company>

    suspend fun findAllStudents(): List<Student>

    suspend fun findAllUserReports(): List<UserReport>

    suspend fun findUserReportById(reportId: Long): UserReport
}


