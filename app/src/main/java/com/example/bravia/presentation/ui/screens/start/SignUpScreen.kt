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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
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

    var selectedOption by remember { mutableStateOf("Select option") }
    var isSelectedOptionValid by remember { mutableStateOf(false) }

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
            shape = RoundedCornerShape(ThemeDefaults.roundedMid),
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

            AccountType(
                selectedOption = selectedOption,
                onSelectedOption = { selectedOption = it },
                isSelectedOptionValid = isSelectedOptionValid,
                onSelectedOptionValidChange = { isSelectedOptionValid = it }
            )

        }

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        ContinueButton(
            isValidEmail = isEmailValid,
            isValidPassword = isPasswordValid,
            isValidConfirmPassword = isConfirmPasswordValid,
            isSelectedOptionValid = isSelectedOptionValid,
            email = email,
            password = password,
            selectedOption = selectedOption,
            navController = navController
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        RedirectToLogin()

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountType(
    selectedOption: String,
    onSelectedOption: (String) -> Unit,
    isSelectedOptionValid: Boolean,
    onSelectedOptionValidChange : (Boolean) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var options = listOf("Student", "Business")

    Text(
        modifier = Modifier.padding(start = ThemeDefaults.textPadding),
        text = "Type of account",
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground
    )

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {

        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(ThemeDefaults.textFieldPadding)
                .clickable { expanded = true }
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelectedOption(option)
                        expanded = false
                        onSelectedOptionValidChange( option != "Select option" )
                    }
                )
            }
        }
    }
}

@Composable
fun RedirectToLogin(

) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        onClick = { /* TODO Acción de ir a login */ },
        enabled = true,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)

    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
fun ContinueButton(
    isValidEmail: Boolean,
    isValidPassword: Boolean,
    isValidConfirmPassword: Boolean,
    isSelectedOptionValid: Boolean,
    email: String,
    password: String,
    selectedOption: String,
    navController: NavController
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 70.dp),
        onClick = { navController.navigate("profileSignup/$email/$password/$selectedOption") },
        enabled = isValidEmail && isValidPassword && isValidConfirmPassword && isSelectedOptionValid
    ) {
        Text(
            text = "Continue ...",
            style = MaterialTheme.typography.headlineSmall
        )

    }
}


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
        isError = !isValid,
        supportingText = { if (showSupportingText && !isValid) Text("Passwords do not match") }
    )
}


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
        isError = !isValid,
        supportingText = { if (showSupportingText && !isValid) Text("The password must be at least 8 characters.") }
    )
}

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
        isError = !isValid,
        supportingText = { if (showSupportingText && !isValid) Text("Ex. mail@mail.com") }
    )
}



