package com.example.bravia.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bravia.domain.repository.CollegeRepository
import com.example.bravia.domain.repository.InterestRepository
import com.example.bravia.domain.usecase.GetAllCollegesUseCase
import com.example.bravia.domain.usecase.GetAllInterestUseCase
import com.example.bravia.domain.usecase.GetInterestByIdUseCase
import com.example.bravia.presentation.viewmodel.SignupViewModel

class SignupViewModelFactory(
    private val interestRepository: InterestRepository,
    private val collegeREpository: CollegeRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(
//                getAllInterestsUseCase = GetAllInterestUseCase(repository),
//                getInterestByIdUseCase = GetInterestByIdUseCase(repository)
                getAllCollegesUseCase = GetAllCollegesUseCase(collegeREpository),
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}