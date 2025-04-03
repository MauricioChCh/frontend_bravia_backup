package com.example.bravia.domain.repository

import com.example.bravia.domain.model.Degree

/**
 * Interface representing a repository for managing degrees.
 *
 * This interface defines the contract for retrieving degree data.
 */
interface DegreeRepository {

    /**
     * Retrieves a list of all degrees.
     *
     * @return A list of [Degree] objects representing all degrees.
     */
    suspend fun getAllDegrees(): Result<List<Degree>>
}