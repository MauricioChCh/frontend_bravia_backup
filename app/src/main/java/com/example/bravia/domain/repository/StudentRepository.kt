package com.example.bravia.domain.repository

import com.example.bravia.domain.model.Company

interface StudentRepository {
    suspend fun getCompanyById(username: String): Result<Company?>
}