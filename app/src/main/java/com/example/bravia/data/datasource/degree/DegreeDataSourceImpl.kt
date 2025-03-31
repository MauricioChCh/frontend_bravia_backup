package com.example.bravia.data.datasource.degree

import com.example.bravia.data.datasource.model.DegreeDTO


/**
 * Implementation of [DegreeDataSource] for accessing degree data.
 *
 * This class provides a method to retrieve a list of all degrees.
 */

class DegreeDataSourceImpl : DegreeDataSource {
    /**
     * Retrieves a list of all degrees.
     *
     * @return A list of [DegreeDTO] representing all degrees.
     */
    override fun getAllDegrees(): List<DegreeDTO> {
        return DegreeProvider.degrees
    }
}