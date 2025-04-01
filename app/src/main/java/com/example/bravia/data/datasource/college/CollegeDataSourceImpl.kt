package com.example.bravia.data.datasource.college

import com.example.bravia.data.remote.dto.CollegeDTO


/**
 * Implementation of [CollegeDataSource] for accessing college data.
 *
 * This class provides a method to retrieve a list of all colleges.
 */
class CollegeDataSourceImpl : CollegeDataSource {
    /**
     * Retrieves a list of all colleges.
     *
     * @return A list of [CollegeDTO] representing all colleges.
     */
    override fun getAllColleges(): List<CollegeDTO> {
        return CollegeProvider.colleges
    }
}