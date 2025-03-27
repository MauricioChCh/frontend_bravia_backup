package com.example.bravia.domain.repository

import com.example.bravia.domain.model.Interest

interface InterestRepository {
    fun getAllInterests(): List<Interest>
    fun getInterestById(id: Long): Interest?
}