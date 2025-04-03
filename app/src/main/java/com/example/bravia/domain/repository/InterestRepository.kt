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
     * @return A [Result] containing a list of [Interest] objects representing all interests.
     */
    suspend fun getAllInterests(): Result<List<Interest>>

    /**
     * Retrieves a list of interests by their category.
     *
     * @param category The category of interests to retrieve.
     * @return A list of [Interest] objects belonging to the specified category.
     */
    suspend fun getInterestById(id: Long): Result<Interest?>
}