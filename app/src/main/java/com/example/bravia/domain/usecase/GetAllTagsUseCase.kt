package com.example.bravia.domain.usecase

import com.example.bravia.domain.model.Tag
import com.example.bravia.domain.repository.CompanyRepository
import javax.inject.Inject

class GetAllTagsUseCase @Inject constructor(
   private val repository: CompanyRepository
) {
    /**
     * Retrieves all tags from the repository.
     *
     * @return A [Result] containing a list of tags or an error.
     */
    suspend operator fun invoke(): Result<List<Tag>> {
        return repository.getAllTags()
    }
}