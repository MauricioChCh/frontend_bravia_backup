package com.example.bravia.domain.repository

import com.example.bravia.domain.model.Degree

interface DegreeRepository {
    fun getAllDegrees(): List<Degree>
}