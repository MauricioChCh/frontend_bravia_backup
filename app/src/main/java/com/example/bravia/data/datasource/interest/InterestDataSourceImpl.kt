package com.example.bravia.data.datasource.interest

import com.example.bravia.data.datasource.model.InterestDTO


/**
 * Implementation of [InterestDataSource] for accessing interest data.
 *
 * This class provides methods to retrieve a list of all interests and to get an interest by its ID.
 */
class InterestDataSourceImpl : InterestDataSource {
    /**
     * Retrieves a list of all interests.
     *
     * @return A list of [InterestDTO] representing all interests.
     */
    override fun getAllInterests(): List<InterestDTO> {
        return InterestProvider.interests
    }

    /**
     * Retrieves an interest by its ID.
     *
     * @param id The ID of the interest to retrieve.
     * @return The [InterestDTO] representing the interest with the specified ID, or null if not found.
     */
    override fun getInterestById(id: Long): InterestDTO? {
        return null
//        return InterestProvider.interests.find { it.id == id }
    }
}