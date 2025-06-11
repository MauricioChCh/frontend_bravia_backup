package com.example.bravia.domain.usecase


import com.example.bravia.domain.model.Modality
import com.example.bravia.domain.repository.ModalityRepository
import javax.inject.Inject

class GetAllInternshipModalitiesUseCase @Inject constructor(private val repository: ModalityRepository) {

    suspend operator fun invoke() : Result<List<Modality>> {
        repository.getAllInternshipsModalities()
    }
}