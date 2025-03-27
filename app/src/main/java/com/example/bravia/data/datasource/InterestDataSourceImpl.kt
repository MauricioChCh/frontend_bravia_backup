package com.example.bravia.data.datasource

import com.example.bravia.data.datasource.model.InterestDTO

class InterestDataSourceImpl : InterestDataSource {
    override fun getAllInterests(): List<InterestDTO> {
        return InterestProvider.interests
    }

    override fun getInterestById(id: Long): InterestDTO? {
        return InterestProvider.interests.find { it.id == id }
    }
}