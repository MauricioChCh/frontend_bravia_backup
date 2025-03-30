package com.example.bravia.data.datasource.college

import com.example.bravia.data.datasource.model.CollegeDTO

interface CollegeDataSource  {
    fun getAllColleges(): List<CollegeDTO>
}