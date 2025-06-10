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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.ui.theme.Typography
import com.example.bravia.presentation.viewmodel.SignupViewModel


/**
 * SignUpScreen is a composable function that represents the sign-up screen of the application.
 * It allows users to enter their email, password, and confirm password.
 *
 * @param navController The NavController used for navigation between screens.
 * @param paddingValues The padding values to be applied to the screen.
 * @param signUpViewModel The ViewModel responsible for handling sign-up logic.
 */
@Composable
fun SignUpScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    signUpViewModel: SignupViewModel
) {
    var email by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf("") }
    var isPasswordValid by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    var confirmPassword by remember { mutableStateOf("") }
    var isConfirmPasswordValid by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(ThemeDefaults.screenPadding)
    ) {
        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge))


        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "BravIA",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge))

        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.displayMedium
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        Card(
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(0.5.dp, Color.Black),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface ),
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

            ConfirmPassword(
                confirmPassword = confirmPassword,
                onConfirmPasswordChange = {
                    confirmPassword = it
                    isConfirmPasswordValid = it == password
                },
                confirmPasswordVisible = confirmPasswordVisible,
                onConfirmPasswordVisibilityChange = { confirmPasswordVisible = !confirmPasswordVisible },
                isValid = isConfirmPasswordValid
            )
        }

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        ContinueButton(
            isValidEmail = isEmailValid,
            isValidPassword = isPasswordValid,
            isValidConfirmPassword = isConfirmPasswordValid,
            email = email,
            password = password,
            navController = navController,
            signupViewModel = signUpViewModel
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        RedirectToLogin(navController)

    }
}

/**
 * RedirectToLogin is a composable function that displays a button to redirect the user to the login screen.
 *
 * @param navController The NavController used for navigation between screens.
 */
@Composable
fun RedirectToLogin(
    navController: NavController
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        onClick = { navController.navigate("signIn") },
        enabled = true,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)

    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

/**
 * ContinueButton is a composable function that displays a button to continue to the next screen.
 *
 * @param isValidEmail Indicates whether the email is valid.
 * @param isValidPassword Indicates whether the password is valid.
 * @param isValidConfirmPassword Indicates whether the confirm password is valid.
 * @param email The email entered by the user.
 * @param password The password entered by the user.
 * @param navController The NavController used for navigation between screens.
 * @param signupViewModel The ViewModel responsible for handling sign-up logic.
 */
@Composable
fun ContinueButton(
    isValidEmail: Boolean,
    isValidPassword: Boolean,
    isValidConfirmPassword: Boolean,
    email: String,
    password: String,
    navController: NavController,
    signupViewModel: SignupViewModel
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 70.dp),
        onClick = {
            signupViewModel.onEmailChange(email)
            signupViewModel.onPasswordChange(password)
            signupViewModel.onConfirmPasswordChange(password)
            navController.navigate("profileSignup")
        },
        enabled = isValidEmail && isValidPassword && isValidConfirmPassword
    ) {
        Text(
            text = "Continue ...",
            style = MaterialTheme.typography.headlineSmall
        )

    }
}


/**
 * ConfirmPassword is a composable function that displays a text field for confirming the password.
 *
 * @param confirmPassword The password entered by the user.
 * @param onConfirmPasswordChange Callback function to handle changes in the confirm password field.
 * @param confirmPasswordVisible Indicates whether the confirm password is visible.
 * @param onConfirmPasswordVisibilityChange Callback function to handle changes in the visibility of the confirm password field.
 * @param isValid Indicates whether the confirm password is valid.
 */
@Composable
fun ConfirmPassword(
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    confirmPasswordVisible: Boolean,
    onConfirmPasswordVisibilityChange: () -> Unit,
    isValid: Boolean
) {
    var showSupportingText by remember { mutableStateOf(false) }

    Text(
        modifier = Modifier.padding(start = ThemeDefaults.textPadding),
        text = "Confirm Password",
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ThemeDefaults.textFieldPadding),
        value = confirmPassword,
        onValueChange = {
            onConfirmPasswordChange(it)
            showSupportingText = true
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val icon = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            Icon(
                imageVector = icon,
                contentDescription = "Toggle password visibility",
                modifier = Modifier.clickable(onClick = onConfirmPasswordVisibilityChange)
            )
        },
        visualTransformation = if (confirmPasswordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        isError = showSupportingText && !isValid,
        placeholder = {
            Text(
                text = "Confirm Password",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        supportingText = { if (showSupportingText && !isValid) Text("Passwords do not match") }
    )
}

/**
 * Password is a composable function that displays a text field for entering the password.
 *
 * @param password The password entered by the user.
 * @param onPasswordChange Callback function to handle changes in the password field.
 * @param passwordVisible Indicates whether the password is visible.
 * @param onPasswordVisibilityChange Callback function to handle changes in the visibility of the password field.
 * @param isValid Indicates whether the password is valid.
 */
@Composable
fun Password(
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit,
    isValid: Boolean
) {
    var showSupportingText by remember { mutableStateOf(false) }

    Text(
        modifier = Modifier.padding(start = ThemeDefaults.textPadding),
        text = "Password",
        style = Typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ThemeDefaults.textFieldPadding),
        value = password,
        onValueChange = {
            onPasswordChange(it)
            showSupportingText = true
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            Icon(
                imageVector = icon,
                contentDescription = "Toggle password visibility",
                modifier = Modifier.clickable(onClick = onPasswordVisibilityChange)
            )
        },
        visualTransformation = if (passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        isError = showSupportingText && !isValid,
        placeholder = {
            Text(
                text = "Password",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        supportingText = { if (showSupportingText && !isValid) Text("The password must be at least 8 characters.") }
    )
}

/**
 * Email is a composable function that displays a text field for entering the email address.
 *
 * @param email The email entered by the user.
 * @param onEmailChange Callback function to handle changes in the email field.
 * @param isValid Indicates whether the email is valid.
 */
@Composable
fun Email(
    email: String,
    onEmailChange: (String) -> Unit,
    isValid: Boolean
) {
    var showSupportingText by remember { mutableStateOf(false) }

    Text(
        modifier = Modifier.padding(start = ThemeDefaults.textPadding),
        text = "Email",
        style = Typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ThemeDefaults.textFieldPadding),
        value = email,
        onValueChange = {
            onEmailChange(it)
            showSupportingText = true
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        maxLines = 1,
        singleLine = true,
        isError = showSupportingText && !isValid,
        placeholder = {
            Text(
                text = "example@example.com",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        supportingText = { if (showSupportingText && !isValid) Text("Ex. mail@mail.com") }
    )
}



