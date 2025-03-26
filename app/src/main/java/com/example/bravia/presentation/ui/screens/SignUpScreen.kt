package com.example.bravia.presentation.ui.screens

import android.content.res.Resources
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.studentapp.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.ui.theme.*
import com.example.bravia.presentation.viewmodel.InternshipViewModel
import com.example.bravia.presentation.viewmodel.SignupViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    paddingValues: PaddingValues,
    signUpViewModel: SignupViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Student", "Business")
    var selectedOption by remember { mutableStateOf("Select option") }

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

            Text(
                modifier = Modifier.padding(start = ThemeDefaults.textPadding),
                text = "Email",
                style = Typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ThemeDefaults.textFieldPadding)
            )

            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

            Text(
                modifier = Modifier.padding(start = ThemeDefaults.textPadding),
                text = "Password",
                style = Typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ThemeDefaults.textFieldPadding)
            )

            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

            Text(
                modifier = Modifier.padding(start = ThemeDefaults.textPadding),
                text = "Confirm Password",
                style = Typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ThemeDefaults.textFieldPadding)
            )

            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

            Text(
                modifier = Modifier.padding(start = ThemeDefaults.textPadding),
                text = "Type of account",
                style = Typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
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
                            }
                        )
                    }
                }
            }

        }

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        // Button
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 70.dp)
        ) {
            Text(
                text = "Continue ...",
                style = Typography.headlineSmall,
            )

        }

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        Button(
            onClick = { /* TODO Acción de ir a login */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
        ) {
            Text(
                text = "Login",
                style = Typography.headlineSmall,
            )
        }

    }
}