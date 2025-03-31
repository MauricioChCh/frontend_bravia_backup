package com.example.bravia.data.datasource.businessArea

import com.example.bravia.data.datasource.model.BusinessAreaDTO


/**
 * Implementation of [BusinessAreaDataSource] for accessing business areas.
 */

class BusinessAreaDataSourceImpl : BusinessAreaDataSource {
    /**
     * Retrieves a list of all business areas.
     *
     * @return A list of [BusinessAreaDTO] representing all business areas.
     */
    override fun getAllBusinessAreas(): List<BusinessAreaDTO> {
        return BusinessAreaProvider.businessAreas
    }
}