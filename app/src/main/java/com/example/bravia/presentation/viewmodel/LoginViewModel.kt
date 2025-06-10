package com.example.bravia.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LoginState {
    data object Initial : LoginState()
    data class Loading(val message: String) : LoginState()
    data class Success(val destination: String) : LoginState()
    data class Error(val message: String) : LoginState()
}

data class LoginUiState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val errorMessage: String? = null,
    val email: String = "",
    val password: String = ""
)


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    // Estado de la pantalla
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    // Estado de eventos (éxito, error, etc.)
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Initial)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    fun login() {
        val email = _uiState.value.email
        val password = _uiState.value.password

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            _loginState.value = LoginState.Loading("Logging in...")

            val result = loginUseCase(email, password)

//            result.onSuccess {
//                _uiState.value = _uiState.value.copy(isLoading = false, isLoggedIn = true)
//                _loginState.value = LoginState.Success("Login successful")
//            }
            result.onSuccess { authResult ->
                println("Resultado: $authResult")
                val roles = authResult.authorities

                Log.d("LoginViewModel", "------------------------------- User roles: ${roles.joinToString(", ")}")
                Log.d("LoginViewModel", " User id: ${authResult.userId}")
                Log.d("LoginViewModel", " User token: ${authResult.token}")
                Log.d("LoginViewModel", " User username: ${authResult.username}")


                val destination = when {
                    authResult.authorities.any { it.authority == "ROLE_ADMIN" } -> "admin_home"
                    authResult.authorities.any { it.authority == "ROLE_COMPANY" } -> "company_home"
                    authResult.authorities.any { it.authority == "ROLE_STUDENT" } -> "home"
                    else -> "home"
                }

                _uiState.value = _uiState.value.copy(isLoading = false, isLoggedIn = true)
                _loginState.value = LoginState.Success(destination)
            }.onFailure { exception ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = exception.message
                )
                _loginState.value = LoginState.Error(exception.message ?: "Unknown error")
            }
        }
    }

    fun resetLoginState() {
        _loginState.value = LoginState.Initial
    }
}
