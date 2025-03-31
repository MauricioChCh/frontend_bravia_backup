package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Degree
import com.example.bravia.domain.repository.DegreeRepository


/**
 * Use case for retrieving all degrees.
 *
 * This use case interacts with the [DegreeRepository] to fetch a list of all degrees.
 *
 * @property repository The repository instance used to retrieve degree data.
 */
class GetAllDegreesUseCase (
    private val repository: DegreeRepository
) {

    /**
     * Invokes the use case to retrieve all degrees.
     *
     * @return A list of [Degree] objects representing all degrees.
     */
    operator fun invoke(): List<Degree> {
        return repository.getAllDegrees()
    }
}