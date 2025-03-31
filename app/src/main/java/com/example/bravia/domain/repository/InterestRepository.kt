package com.example.bravia.domain.repository

import com.example.bravia.domain.model.Interest


/**
 * Interface representing a repository for managing interests.
 *
 * This interface defines the contract for retrieving interest data.
 */
interface InterestRepository {

    /**
     * Retrieves a list of all interests.
     *
     * @return A list of [Interest] objects representing all interests.
     */
    fun getAllInterests(): List<Interest>

    /**
     * Retrieves a list of interests by their category.
     *
     * @param category The category of interests to retrieve.
     * @return A list of [Interest] objects belonging to the specified category.
     */
    fun getInterestById(id: Long): Interest?
}