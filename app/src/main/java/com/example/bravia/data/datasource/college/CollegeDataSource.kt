package com.example.bravia.data.datasource.college

import com.example.bravia.data.remote.dto.CollegeDTO


/**
 * Data source interface for accessing college data.
 */
interface CollegeDataSource  {
    /**
     * Retrieves a list of all colleges.
     *
     * @return A list of [CollegeDTO] representing all colleges.
     */
    fun getAllColleges(): List<CollegeDTO>
}