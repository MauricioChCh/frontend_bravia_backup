package com.example.bravia.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.data.local.AuthPreferences
import com.example.bravia.domain.model.UserRole
import com.example.bravia.domain.model.UserSession
import com.example.bravia.domain.usecase.LoginUseCase
import com.example.bravia.domain.usecase.LogoutUseCase
import com.example.bravia.presentation.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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
    object NotLoggedIn : LoginState()
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
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val authPreferences: AuthPreferences
) : ViewModel() {

    private val navigationManager = NavigationManager()
    // Estado de la pantalla
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    // Estado de eventos (éxito, error, etc.)
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Initial)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    // Estado de la sesión del usuario
    private val _userSession = MutableStateFlow<UserSession?>(null)
    val userSession: StateFlow<UserSession?> = _userSession.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private val _logoutCompleted = MutableStateFlow(false)
    val logoutCompleted: StateFlow<Boolean> = _logoutCompleted.asStateFlow()

    init {
        // Verificar el estado de autenticación al inicializar
        checkAuthenticationStatus()
    }
    private fun checkAuthenticationStatus() {
        viewModelScope.launch {
            try {
                val isAuthenticated = authPreferences.isAuthenticated()
                _isLoggedIn.value = isAuthenticated

                if (isAuthenticated) {
                    // No hace falta pero recreamos la sesión del usuario basica
                    val token = authPreferences.getAuthToken()
                    val username = authPreferences.getUsername()

                    if (token != null && username != null) {
                        // Crear una sesión básica
                        val basicSession = UserSession(
                            userId = "",
                            username = username,
                            token = token,
                            role = UserRole.STUDENT,
                            authorities = emptyList()
                        )
                        _userSession.value = basicSession
                    }
                }

                Log.d("LoginViewModel", "Authentication status checked: $isAuthenticated")
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error checking authentication status", e)
                _isLoggedIn.value = false
            }
        }
    }


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

            result.onSuccess { authResult ->
                println("Resultado: $authResult")
                val authorities = authResult.authorities.map { it.authority }
                val userRole = UserRole.fromAuthorities(authorities)

                // Crear sesión de usuario
                val session = UserSession(
                    userId = authResult.userId,
                    username = authResult.username,
                    token = authResult.token,
                    role = userRole,
                    authorities = authorities
                )

                _userSession.value = session

                //Persistencia
                authPreferences.saveAuthToken(authResult.token)
                authPreferences.saveUsername(authResult.username)
                authPreferences.saveLoginState(true)

                // Obtener destino basado en el rol
                val destination = navigationManager.getPostLoginDestination(authorities)

                Log.d("LoginViewModel", "User roles: ${authorities.joinToString(", ")}")
                Log.d("LoginViewModel", "User role: ${userRole}")
                Log.d("LoginViewModel", "Destination: $destination")
                _isLoggedIn.value = true // Actualizar estado de autenticación
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

//    fun logout() {
//        _userSession.value = null
//        _uiState.value = LoginUiState()
//        _loginState.value = LoginState.Idle
//    }


    fun logout() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Limpieza local primero (rápido y garantizado)
                clearUserSession()

                // Opcional: Llamada remota si necesitas notificar al backend
                // (pero no es crítica para la seguridad en este enfoque)
                logoutUseCase().onFailure { e ->
                    Log.e("LoginViewModel", "Logout remoto falló (no crítico): ${e.message}")
                }
                _logoutCompleted.value = true
                // Redirige a login o muestra confirmación
                _loginState.value = LoginState.NotLoggedIn

            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error en logout: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private suspend fun clearUserSession() {
        // 1. Borra el token del almacenamiento persistente
        authPreferences.clearAuthData()

        // 2. Limpia estados en memoria
        _userSession.value = null
        _isLoggedIn.value = false
        _uiState.value = LoginUiState()

        // 3. Log para debug
        Log.d("LoginViewModel", "Sesión local limpiada")
    }

    // Función para verificar si el logout fue exitoso
    fun isLogoutComplete(): Boolean {
        return !_isLoggedIn.value && _userSession.value == null
    }


    //Flow de persistencia existente
    fun isAuthenticatedFlow(): Flow<Boolean> {
        return authPreferences.isAuthenticatedFlow()
    }

    /**
     * Verifica el estado de autenticación persistente
     */
    suspend fun isPersistentlyAuthenticated(): Boolean {
        return authPreferences.isAuthenticated()
    }

    fun resetLogoutState() {
        _logoutCompleted.value = false
    }

}
