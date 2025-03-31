package com.example.bravia.data.datasource.interest

import com.example.bravia.data.datasource.model.InterestDTO

/**
 * Data source interface for accessing interest data.
 *
 * This interface defines methods to retrieve a list of all interests and to get an interest by its ID.
 */

interface InterestDataSource {
    /**
     * Retrieves a list of all interests.
     *
     * @return A list of [InterestDTO] representing all interests.
     */
    fun getAllInterests(): List<InterestDTO>

    /**
     * Retrieves an interest by its ID.
     *
     * @param id The ID of the interest to retrieve.
     * @return The [InterestDTO] representing the interest with the specified ID, or null if not found.
     */
    fun getInterestById(id: Long): InterestDTO?
}