package com.example.bravia.data.datasource.college

import com.example.bravia.data.datasource.model.CollegeDTO

class CollegeDataSourceImpl : CollegeDataSource {
    override fun getAllColleges(): List<CollegeDTO> {
        return CollegeProvider.colleges
    }
}