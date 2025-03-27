package com.example.bravia.data.datasource

import com.example.bravia.data.datasource.model.InterestDTO

interface InterestDataSource {
    fun getAllInterests(): List<InterestDTO>
    fun getInterestById(id: Long): InterestDTO?
}