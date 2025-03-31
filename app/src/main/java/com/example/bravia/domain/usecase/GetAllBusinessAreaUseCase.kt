package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.BusinessArea
import com.example.bravia.domain.repository.BusinessAreaRepository

/**
 * Use case for retrieving all business areas.
 *
 * This use case interacts with the [BusinessAreaRepository] to fetch a list of all business areas.
 *
 * @property repository The repository instance used to retrieve business area data.
 */
class GetAllBusinessAreaUseCase (
    private val repository: BusinessAreaRepository
) {
    /**
     * Invokes the use case to retrieve all business areas.
     *
     * @return A list of [BusinessArea] objects representing all business areas.
     */
    operator fun invoke(): List<BusinessArea> {
        return repository.getAllBusinessAreas()
    }
}