package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.College
import com.example.bravia.domain.repository.CollegeRepository


/**
 * Use case for retrieving a list of all colleges.
 *
 * @property repository The repository to fetch college data from.
 */
class GetAllCollegesUseCase (
    private val repository: CollegeRepository
) {

    /**
     * Invokes the use case to retrieve all colleges.
     *
     * @return A list of [College] objects representing all colleges.
     */
    operator fun invoke(): List<College> {
        return repository.getAllColleges()
    }
}