package com.example.bravia.presentation.ui.screens.start


import android.util.Patterns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import com.example.bravia.presentation.viewmodel.LoginViewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import com.example.bravia.presentation.viewmodel.LoginState

import com.example.bravia.presentation.ui.theme.ThemeHelper as Theme

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

    val uiState by loginViewModel.uiState.collectAsState()
    val loginState by loginViewModel.loginState.collectAsState()

    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginState.Success -> {
                val destination = (loginState as LoginState.Success).destination
                navController.navigate(destination)
                loginViewModel.resetLoginState()
            }

            is LoginState.Error -> {
                // Mostrar Snackbar/Dialog
            }

            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(ThemeDefaults.screenPadding)
    ) {
        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge*3))


        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "BravIA",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge))


        Text(
            text = "Sign In",
            style = MaterialTheme.typography.displayMedium
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

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
                },
                isValid = isEmailValid
            )

            Password(
                password = password,
                onPasswordChange = {
                    password = it
                    isPasswordValid = it.length >= 8
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
//            navController.navigate("home")
        },
        enabled = isValidEmail && isValidPassword
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
