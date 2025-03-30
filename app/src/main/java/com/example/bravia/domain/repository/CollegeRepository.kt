package com.example.bravia.domain.repository

import com.example.bravia.domain.model.College

interface CollegeRepository {
    fun getAllColleges(): List<College>
}