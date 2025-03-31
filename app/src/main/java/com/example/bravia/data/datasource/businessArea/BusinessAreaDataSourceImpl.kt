package com.example.bravia.data.datasource.businessArea

import com.example.bravia.data.datasource.model.BusinessAreaDTO

class BusinessAreaDataSourceImpl : BusinessAreaDataSource {
    override fun getAllBusinessAreas(): List<BusinessAreaDTO> {
        return BusinessAreaProvider.businessAreas
    }
}