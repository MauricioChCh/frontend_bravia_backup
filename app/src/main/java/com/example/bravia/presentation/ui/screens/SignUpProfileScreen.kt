package com.example.bravia.presentation.ui.screens

import android.accounts.Account
import android.util.Log
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bravia.presentation.ui.theme.Typography
import com.example.bravia.presentation.viewmodel.SignupViewModel
import com.example.studentapp.presentation.ui.theme.ThemeDefaults


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpProfileScreen (
    paddingValues: PaddingValues,
    signupViewModel: SignupViewModel,
    email: String,
    password: String,
    typeAccount: String
) {

    Log.d("SignUpProfileScreen", "email: $email, password: $password, typeAccount: $typeAccount")

    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }

    var recruiterName by remember { mutableStateOf("") }
    var recruiterLastname by remember { mutableStateOf("") }
    var companyName by remember { mutableStateOf("") }
    val optionsBusinessArea = listOf("Info", "Secre") // TODO: Change this
    var selectedOptionBussinesArea by remember { mutableStateOf("Select option") }
    var expandedBusinessArea by remember { mutableStateOf(false) }
    var expandedCollege by remember { mutableStateOf(false) }
    val optionsCollege = listOf("Tecnico", "Academico") // TODO: Change this
    var selectedOptionCollege by remember { mutableStateOf("Select option") }
    var expandedDegree by remember { mutableStateOf(false) }
    val optionsDegree = listOf("College", "University")
    var selectedOptionDegree by remember { mutableStateOf("Select option") }

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



        if (typeAccount == "Student") {
            Text(
                text = "Student Sign Up",
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
                    text = "Name",
                    style = Typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ThemeDefaults.textFieldPadding)
                )

                Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

                Text(
                    modifier = Modifier.padding(start = ThemeDefaults.textPadding),
                    text = "Last Name",
                    style = Typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    value = lastname,
                    onValueChange = { lastname = it },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ThemeDefaults.textFieldPadding)
                )

                Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

                Text(
                    modifier = Modifier.padding(start = ThemeDefaults.textPadding),
                    text = "College",
                    style = Typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                ExposedDropdownMenuBox(
                    expanded = expandedCollege,
                    onExpandedChange = { expandedCollege = !expandedCollege }
                ) {

                    OutlinedTextField(
                        value = selectedOptionCollege,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(ThemeDefaults.textFieldPadding)
                            .clickable { expandedCollege = true }
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedCollege,
                        onDismissRequest = { expandedCollege = false }
                    ) {
                        optionsCollege.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    selectedOptionCollege = option
                                    expandedCollege = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

                Text(
                    modifier = Modifier.padding(start = ThemeDefaults.textPadding),
                    text = "Academic degree",
                    style = Typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                ExposedDropdownMenuBox(
                    expanded = expandedDegree,
                    onExpandedChange = { expandedDegree = !expandedDegree }
                ) {

                    OutlinedTextField(
                        value = selectedOptionDegree,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(ThemeDefaults.textFieldPadding)
                            .clickable { expandedDegree = true }
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedDegree,
                        onDismissRequest = { expandedDegree = false }
                    ) {
                        optionsDegree.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    selectedOptionDegree = option
                                    expandedDegree = false
                                }
                            )
                        }
                    }
                }

            }


        } else {
            Text(
                text = "Business Sign Up",
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
                    text = "Recruiter Name",
                    style = Typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    value = recruiterName,
                    onValueChange = { recruiterName = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ThemeDefaults.textFieldPadding)
                )

                Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

                Text(
                    modifier = Modifier.padding(start = ThemeDefaults.textPadding),
                    text = "Recruiter Last Name",
                    style = Typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    value = recruiterLastname,
                    onValueChange = { lastname = it },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ThemeDefaults.textFieldPadding)
                )

                Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

                Text(
                    modifier = Modifier.padding(start = ThemeDefaults.textPadding),
                    text = "Company Name",
                    style = Typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    value = companyName,
                    onValueChange = { companyName = it },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ThemeDefaults.textFieldPadding)
                )

                Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

                Text(
                    modifier = Modifier.padding(start = ThemeDefaults.textPadding),
                    text = "Business Area",
                    style = Typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                ExposedDropdownMenuBox(
                    expanded = expandedBusinessArea,
                    onExpandedChange = { expandedBusinessArea = !expandedBusinessArea }
                ) {

                    OutlinedTextField(
                        value = selectedOptionBussinesArea,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(ThemeDefaults.textFieldPadding)
                            .clickable { expandedBusinessArea = true }
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedBusinessArea,
                        onDismissRequest = { expandedBusinessArea = false }
                    ) {
                        optionsBusinessArea.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    selectedOptionBussinesArea = option
                                    expandedBusinessArea = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))


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