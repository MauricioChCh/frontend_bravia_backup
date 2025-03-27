package com.example.bravia.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bravia.domain.repository.InterestRepository
import com.example.bravia.domain.usecase.GetAllInterestUseCase
import com.example.bravia.domain.usecase.GetInterestByIdUseCase
import com.example.bravia.presentation.viewmodel.SignupViewModel

class SignupViewModelFactory(
    private val repository: InterestRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(
                getAllInterestsUseCase = GetAllInterestUseCase(repository),
                getInterestByIdUseCase = GetInterestByIdUseCase(repository)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}