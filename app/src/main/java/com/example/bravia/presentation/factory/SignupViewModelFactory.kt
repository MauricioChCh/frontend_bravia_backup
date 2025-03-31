package com.example.bravia.presentation.factory

    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.ViewModelProvider
    import com.example.bravia.domain.repository.CollegeRepository
    import com.example.bravia.domain.repository.DegreeRepository
    import com.example.bravia.domain.repository.InterestRepository
    import com.example.bravia.domain.usecase.GetAllCollegesUseCase
    import com.example.bravia.domain.usecase.GetAllDegreesUseCase
    import com.example.bravia.domain.usecase.GetAllInterestUseCase
    import com.example.bravia.domain.usecase.GetInterestByIdUseCase
    import com.example.bravia.presentation.viewmodel.SignupViewModel

    class SignupViewModelFactory(
        private val interestRepository: InterestRepository,
        private val collegeRepository: CollegeRepository,
        private val degreeRepository: DegreeRepository,
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
                return SignupViewModel(
                    getAllInterestsUseCase = GetAllInterestUseCase(interestRepository),
                    getInterestByIdUseCase = GetInterestByIdUseCase(interestRepository),
                    getAllCollegesUseCase = GetAllCollegesUseCase(collegeRepository),
                    getAllDegreesUseCase = GetAllDegreesUseCase(degreeRepository),
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }