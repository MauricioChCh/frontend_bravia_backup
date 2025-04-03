package com.example.bravia.domain.repository

import com.example.bravia.domain.model.BusinessArea

/**
 * Interface representing a repository for managing business areas.
 *
 * This interface defines the contract for retrieving business area data.
 */
interface BusinessAreaRepository {

    /**
     * Retrieves a list of all business areas.
     *
     * @return A list of [BusinessArea] objects representing all business areas.
     */
    suspend fun getAllBusinessAreas(): Result<List<BusinessArea>>
}