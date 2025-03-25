package com.example.bravia.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bravia.domain.repository.InternshipRepository
import com.example.bravia.domain.usecase.BookmarkInternshipUseCase
import com.example.bravia.domain.usecase.GetAllInternshipsUseCase
import com.example.bravia.domain.usecase.GetBookmarkedInternshipsUseCase
import com.example.bravia.domain.usecase.GetInternshipByIdUseCase
import com.example.bravia.presentation.viewmodel.InternshipViewModel

class InternshipViewModelFactory(
    private val repository: InternshipRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InternshipViewModel::class.java)) {
            return InternshipViewModel(
                getAllInternshipsUseCase = GetAllInternshipsUseCase(repository),
                getInternshipByIdUseCase = GetInternshipByIdUseCase(repository),
                bookmarkInternshipUseCase = BookmarkInternshipUseCase(repository),
                getBookmarkedInternshipsUseCase = GetBookmarkedInternshipsUseCase(repository)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}