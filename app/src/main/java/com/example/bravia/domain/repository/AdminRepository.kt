package com.example.bravia.domain.repository

import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.Student

interface AdminRepository {

    suspend fun findAllCompanies(): List<Company>

    suspend fun findAllStudents(): List<Student>

}


