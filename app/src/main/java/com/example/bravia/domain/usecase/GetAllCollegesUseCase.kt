package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.College
import com.example.bravia.domain.repository.CollegeRepository
import javax.inject.Inject


/**
 * Use case for retrieving a list of all colleges.
 *
 * @property repository The repository to fetch college data from.
 */
class GetAllCollegesUseCase @Inject constructor (
    private val repository: CollegeRepository
) {

    /**
     * Invokes the use case to retrieve all colleges.
     *
     * @return A list of [College] objects representing all colleges.
     */
    suspend operator fun invoke(): Result<List<College>> {
        return repository.getAllColleges()
    }
}