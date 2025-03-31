package com.example.bravia.data.datasource.degree

import com.example.bravia.data.datasource.model.DegreeDTO


/**
 * Data source interface for accessing degree data.
 *
 * This interface defines a method to retrieve a list of all degrees.
 */
interface DegreeDataSource {
    /**
     * Retrieves a list of all degrees.
     *
     * @return A list of [DegreeDTO] representing all degrees.
     */
    fun getAllDegrees(): List<DegreeDTO>
}