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
     * @return A list of [College] objects representing all colleges.
     */
    fun getAllColleges(): List<College>
}