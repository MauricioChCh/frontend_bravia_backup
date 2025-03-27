package com.example.bravia.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

sealed class SignUPState {
    data class Loading(val message: String) : SignUPState()
    data class Success(val message: String) : SignUPState()
    data class Error(val message: String) : SignUPState()
}

class SignupViewModel (

) : ViewModel() {
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var confirmPassword by mutableStateOf("")
        private set

    fun onEmailChange(email: String) {
        this.email = email
    }

    fun onPasswordChange(password: String) {
        this.password = password
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        this.confirmPassword = confirmPassword
    }

    fun signUp() {
        // Implement sign up logic here
    }

}