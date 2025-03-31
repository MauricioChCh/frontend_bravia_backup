package com.example.bravia.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bravia.domain.repository.BusinessAreaRepository
import com.example.bravia.domain.repository.CollegeRepository
import com.example.bravia.domain.repository.DegreeRepository
import com.example.bravia.domain.repository.InterestRepository
import com.example.bravia.domain.usecase.GetAllBusinessAreaUseCase
import com.example.bravia.domain.usecase.GetAllCollegesUseCase
import com.example.bravia.domain.usecase.GetAllDegreesUseCase
import com.example.bravia.domain.usecase.GetAllInterestUseCase
import com.example.bravia.domain.usecase.GetInterestByIdUseCase
import com.example.bravia.presentation.viewmodel.SignupViewModel



/**
 * Factory class to create an instance of SignupViewModel.
 * This class is responsible for providing the necessary dependencies
 *
 * @property interestRepository Repository for managing interests.
 * @property collegeRepository Repository for managing colleges.
 * @property degreeRepository Repository for managing degrees.
 * @property businessAreaRepository Repository for managing business areas.
 */
class SignupViewModelFactory(
    private val interestRepository: InterestRepository,
    private val collegeRepository: CollegeRepository,
    private val degreeRepository: DegreeRepository,
    private val businessAreaRepository: BusinessAreaRepository
) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of the specified ViewModel class.
     *
     * @param modelClass The class of the ViewModel to create.
     * @return An instance of the specified ViewModel class.
     * @throws IllegalArgumentException if the ViewModel class is unknown.
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(
                getAllInterestsUseCase = GetAllInterestUseCase(interestRepository),
                getInterestByIdUseCase = GetInterestByIdUseCase(interestRepository),
                getAllCollegesUseCase = GetAllCollegesUseCase(collegeRepository),
                getAllDegreesUseCase = GetAllDegreesUseCase(degreeRepository),
                getAllBusinessAreasUseCase = GetAllBusinessAreaUseCase(businessAreaRepository)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}