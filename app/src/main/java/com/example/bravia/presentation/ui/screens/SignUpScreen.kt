package com.example.bravia.presentation.ui.screens


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
import com.example.studentapp.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.ui.theme.*
import com.example.bravia.presentation.viewmodel.SignupViewModel




@Composable
fun SignUpScreen(
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
            style = Typography.displayLarge
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge))

        Text(
            text = "Sign Up",
            style = Typography.displayMedium,
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

            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

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

            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

            AccountType(
                isSelectedOptionValid = isSelectedOptionValid,
                onSelectedOptionValidChange = { isSelectedOptionValid = it }
            )

        }

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        ContinueButton(
            isValidEmail = isEmailValid,
            isValidPassword = isPasswordValid,
            isValidConfirmPassword = isConfirmPasswordValid,
            isSelectedOptionValid = isSelectedOptionValid
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        RedirectToLogin()

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountType(
    isSelectedOptionValid: Boolean,
    onSelectedOptionValidChange : (Boolean) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var options = listOf("Student", "Business")
    var selectedOption by remember { mutableStateOf("Select option") }

    Text(
        modifier = Modifier.padding(start = ThemeDefaults.textPadding),
        text = "Type of account",
        style = Typography.headlineSmall,
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
                        selectedOption = option
                        expanded = false
                        onSelectedOptionValidChange(selectedOption != "Select option")
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
        enabled = true
    ) {
        Text(
            text = "Login",
            style = Typography.headlineSmall,
        )
    }
}

@Composable
fun ContinueButton(
    isValidEmail: Boolean,
    isValidPassword: Boolean,
    isValidConfirmPassword: Boolean,
    isSelectedOptionValid: Boolean
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 70.dp),
        onClick = { /*TODO*/ },
        enabled = isValidEmail && isValidPassword && isValidConfirmPassword && isSelectedOptionValid
    ) {
        Text(
            text = "Continue ...",
            style = Typography.headlineSmall,
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
    Text(
        modifier = Modifier.padding(start = ThemeDefaults.textPadding),
        text = "Confirm Password",
        style = Typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ThemeDefaults.textFieldPadding),
        value = confirmPassword,
        onValueChange = onConfirmPasswordChange,
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
        isError = !isValid
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
        onValueChange = onPasswordChange,
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
        isError = !isValid
    )
}

@Composable
fun Email(
    email: String,
    onEmailChange: (String) -> Unit,
    isValid: Boolean
) {
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
        onValueChange = onEmailChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        maxLines = 1,
        singleLine = true,
        isError = !isValid
    )
}



