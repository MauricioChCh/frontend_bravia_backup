package com.example.bravia.presentation.ui.screens.start

import android.util.Patterns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.viewmodel.LoginState
import com.example.bravia.presentation.viewmodel.LoginViewModel
import kotlinx.coroutines.delay

@Composable
fun SignInScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    loginViewModel: LoginViewModel
) {
    var email by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf("") }
    var isPasswordValid by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    // Estado para manejar errores
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var showErrorSnackbar by remember { mutableStateOf(false) }

    val uiState by loginViewModel.uiState.collectAsState()
    val loginState by loginViewModel.loginState.collectAsState()

    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginState.Success -> {
                val destination = (loginState as LoginState.Success).destination
                navController.navigate(destination)
                loginViewModel.resetLoginState()
                errorMessage = null
            }

            is LoginState.Error -> {
                val error = loginState as LoginState.Error
                errorMessage = when {
                    error.message.contains("Unauthorized", ignoreCase = true) ->
                        "Credenciales incorrectas. Por favor, verifica e intenta nuevamente."
                    error.message.contains("network", ignoreCase = true) ||
                            error.message.contains("connect", ignoreCase = true) ->
                        "Error de conexión. Verifica tu internet e intenta nuevamente."
                    else -> "Error al iniciar sesión. Por favor, intenta nuevamente."
                }
                showErrorSnackbar = true
            }

            else -> {}
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ThemeDefaults.screenPadding)
        ) {
            // Espaciador dinámico: menos espacio si hay error
            Spacer(modifier = Modifier.height(
                if (errorMessage != null) ThemeDefaults.spacerHeightLarge
                else ThemeDefaults.spacerHeightLarge * 3
            ))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "BravIA",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayLarge
            )

            // Espaciador dinámico: menos espacio si hay error
            Spacer(modifier = Modifier.height(
                if (errorMessage != null) ThemeDefaults.spacerHeight
                else ThemeDefaults.spacerHeightLarge
            ))

            Text(
                text = "Sign In",
                style = MaterialTheme.typography.displayMedium
            )

            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

            // Mostrar error como tarjeta si existe
            errorMessage?.let { error ->
                ErrorCard(
                    message = error,
                    onDismiss = {
                        errorMessage = null
                        loginViewModel.resetLoginState()
                    }
                )
                Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(0.5.dp, Color.Black),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(ThemeDefaults.cardElevation)
            ) {
                Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

                Email(
                    email = email,
                    onEmailChange = {
                        email = it
                        isEmailValid = Patterns.EMAIL_ADDRESS.matcher(it).matches()
                        // Limpiar error cuando el usuario empiece a escribir
                        if (errorMessage != null) {
                            errorMessage = null
                            loginViewModel.resetLoginState()
                        }
                    },
                    isValid = isEmailValid
                )

                Password(
                    password = password,
                    onPasswordChange = {
                        password = it
                        isPasswordValid = it.length >= 8
                        // Limpiar error cuando el usuario empiece a escribir
                        if (errorMessage != null) {
                            errorMessage = null
                            loginViewModel.resetLoginState()
                        }
                    },
                    passwordVisible = passwordVisible,
                    onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
                    isValid = isPasswordValid
                )

                Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

                ForgotPassword()
            }

            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

            ContinueButton(
                isValidEmail = isEmailValid,
                isValidPassword = isPasswordValid,
                email = email,
                password = password,
                navController = navController,
                loginViewModel = loginViewModel
            )

            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

            RedirectToSignup(navController)
        }

        // Snackbar alternativo (opcional)
        if (showErrorSnackbar && errorMessage != null) {
            LaunchedEffect(showErrorSnackbar) {
                delay(4000) // Auto-dismiss después de 4 segundos
                showErrorSnackbar = false
            }
        }
    }
}

@Composable
fun ErrorCard(
    message: String,
    onDismiss: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.error),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Error",
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = onDismiss,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Cerrar",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun ContinueButton(
    isValidEmail: Boolean,
    isValidPassword: Boolean,
    email: String,
    password: String,
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    val uiState by loginViewModel.uiState.collectAsState()
    val isLoading = uiState.isLoading

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 70.dp),
        onClick = {
            loginViewModel.onEmailChange(email)
            loginViewModel.onPasswordChange(password)
            loginViewModel.login()
        },
        enabled = isValidEmail && isValidPassword && !isLoading
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(24.dp)
            )
        } else {
            Text(
                text = "Continue ...",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
fun RedirectToSignup(
    navController: NavController
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        onClick = { navController.navigate("signup") },
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
    ) {
        Text(
            text = "Create an Account",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
fun ForgotPassword() {
    Text(
        text = "Forgot Password?",
        modifier = Modifier
            .clickable {
                // TODO: Implementar lógica de recuperación
            },
        style = TextStyle(
            color = Color(0, 128, 255),
            textDecoration = TextDecoration.Underline
        )
    )
}

