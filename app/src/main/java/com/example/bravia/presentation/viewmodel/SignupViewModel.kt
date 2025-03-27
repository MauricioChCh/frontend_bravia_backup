package com.example.bravia.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bravia.domain.usecase.GetAllInterestUseCase
import com.example.bravia.domain.usecase.GetInterestByIdUseCase
import kotlinx.coroutines.flow.StateFlow

sealed class SignUPState {
    data class Loading(val message: String) : SignUPState()
    data class Success(val message: String) : SignUPState()
    data class Error(val message: String) : SignUPState()
}

class SignupViewModel (
    private val getAllInterestsUseCase: GetAllInterestUseCase,
    private val getInterestByIdUseCase: GetInterestByIdUseCase
) : ViewModel() {



    fun findAllInterests() {
        val interests = getAllInterestsUseCase()
        // Handle the list of interests as needed
    }

    fun signUp() {
        // Implement sign up logic here
    }

}