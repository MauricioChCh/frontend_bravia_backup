package com.example.bravia.domain.repository

import com.example.bravia.domain.model.College

/**
 * Interface representing a repository for managing colleges.
 *
 * This interface defines the contract for retrieving college data.
 */
interface CollegeRepository {

    /**
     * Retrieves a list of all colleges.
     *
     * @return A [Result] containing a list of [College] objects representing all colleges.
     */
    suspend fun getAllColleges(): Result<List<College>>
}