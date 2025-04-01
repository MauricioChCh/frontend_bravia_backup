package com.example.bravia.data.datasource.businessArea

import com.example.bravia.data.remote.dto.BusinessAreaDTO


/**
 * Data source interface for accessing business areas.
 */



interface BusinessAreaDataSource {
    /**
     * Retrieves a list of all business areas.
     *
     * @return A list of [BusinessAreaDTO] representing all business areas.
     */
    fun getAllBusinessAreas(): List<BusinessAreaDTO>
}