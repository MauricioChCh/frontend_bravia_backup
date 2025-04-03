package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Interest
import com.example.bravia.domain.repository.InterestRepository
import javax.inject.Inject


/**
 * Use case for retrieving all interests.
 *
 * This use case interacts with the [InterestRepository] to fetch a list of all interests.
 *
 * @property repository The repository responsible for managing interest data.
 */
class GetAllInterestUseCase @Inject constructor (
    private val repository: InterestRepository
) {

    /**
     * Invokes the use case to retrieve all interests.
     *
     * @return A list of [Interest] objects representing all interests.
     */
    suspend operator fun invoke(): Result<List<Interest>> {
        return repository.getAllInterests()
    }
}