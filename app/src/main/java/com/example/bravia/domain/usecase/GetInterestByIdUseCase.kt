package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Interest
import com.example.bravia.domain.repository.InterestRepository

/**
 * Use case for retrieving an interest by its ID.
 *
 * This use case interacts with the [InterestRepository] to fetch a specific interest based on its ID.
 *
 * @property repository The repository responsible for managing interest data.
 */
class GetInterestByIdUseCase (private val repository: InterestRepository) {

    /**
     * Invokes the use case to retrieve an interest by its ID.
     *
     * @param id The ID of the interest to retrieve.
     * @return The [Interest] object corresponding to the provided ID, or null if not found.
     */
    operator fun invoke(id: Long): Interest? {
        return repository.getInterestById(id)
    }
}