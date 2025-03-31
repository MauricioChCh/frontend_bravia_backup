package com.example.bravia.data.datasource.businessArea

import com.example.bravia.data.datasource.model.BusinessAreaDTO

interface BusinessAreaDataSource {
    fun getAllBusinessAreas(): List<BusinessAreaDTO>
}