package com.example.bravia.domain.repository

import com.example.bravia.domain.model.Modality

interface ModalityRepository {

    suspend fun getAllInternshipsModalities(): Result<List<Modality>>
}