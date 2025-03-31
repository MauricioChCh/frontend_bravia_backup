package com.example.bravia.domain.repository

import com.example.bravia.domain.model.BusinessArea

interface BusinessAreaRepository {
    fun getAllBusinessAreas(): List<BusinessArea>
}