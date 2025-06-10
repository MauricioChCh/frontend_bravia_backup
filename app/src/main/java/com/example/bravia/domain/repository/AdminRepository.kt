package com.example.bravia.domain.repository

import com.example.bravia.domain.model.Company

interface AdminRepository {

    suspend fun findAllCompanies(): List<Company>

}


