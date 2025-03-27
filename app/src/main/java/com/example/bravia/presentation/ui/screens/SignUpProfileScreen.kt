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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.presentation.ui.theme.Typography
import com.example.bravia.presentation.viewmodel.SignupViewModel
import com.example.studentapp.presentation.ui.theme.ThemeDefaults


@Composable
fun SignUpProfileScreen (
    navController: NavController,
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

    var isSelectedOptionCollegeValid by remember { mutableStateOf(false) }
    var isSelectedOptionBusinessValid by remember { mutableStateOf(false) }
    var isSelectedOptionDegreeValid by remember { mutableStateOf(false) }

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

            Student(
                name = name,
                onNameChange = { name = it },
                lastname = lastname,
                onLastNameChange = { lastname = it },
                isSelectedOptionCollegeValid = isSelectedOptionCollegeValid,
                onSelectedOptionCollegeValidChange = { isSelectedOptionCollegeValid = it },
                isSelectedOptionDegreeValid = isSelectedOptionDegreeValid,
                onSelectedOptionDegreeValidChange = { isSelectedOptionDegreeValid = it }
            )

        } else {

            Business(
                recruiterName = recruiterName,
                onRecruiterName = { recruiterName = it },
                recruiterLastname = recruiterLastname,
                onRecruiterLastname = { recruiterLastname = it },
                companyName = companyName,
                onCompanyName = { companyName = it },
                isSelectedOptionBusinessValid = isSelectedOptionBusinessValid,
                onSelectedOptionBusinessValidChange = { isSelectedOptionBusinessValid = it }
            )

        }

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        // Button
        ContinueButton(
            typeAccount = typeAccount,
            navController = navController,
            studentName = name,
            studentLastname = lastname,
            recruiterName = recruiterName,
            recruiterLastname = recruiterLastname,
            companyName = companyName,
            isSelectedOptionCollegeValid = isSelectedOptionCollegeValid,
            isSelectedOptionBusinessValid = isSelectedOptionBusinessValid,
            isSelectedOptionDegreeValid = isSelectedOptionDegreeValid
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        RedirectToLogin(navController)
    }
}

@Composable
fun RedirectToLogin(
    navController: NavController
) {
    Button(
        onClick = { /* TODO Acción de ir a login */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
fun ContinueButton(
    typeAccount: String,
    navController: NavController,
    studentName: String,
    studentLastname: String,
    recruiterName: String,
    recruiterLastname: String,
    companyName: String,
    isSelectedOptionCollegeValid: Boolean,
    isSelectedOptionBusinessValid: Boolean,
    isSelectedOptionDegreeValid: Boolean
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 70.dp),
        onClick = {
            if (typeAccount == "Student") {
                navController.navigate("interestsSignup")
            } else if (typeAccount == "Business") {
                navController.navigate("home")
            }
        },
        enabled = when (typeAccount) {
            "Student" -> studentName.isNotEmpty() && studentLastname.isNotEmpty() && isSelectedOptionCollegeValid && isSelectedOptionDegreeValid
            "Business" -> recruiterName.isNotEmpty() && recruiterLastname.isNotEmpty() && companyName.isNotEmpty() && isSelectedOptionBusinessValid
            else -> false
        }
    ) {
        Text(
            text = if (typeAccount == "Student") {
                "Continue ..."
            } else {
                "Create Account"
            },
            style = MaterialTheme.typography.headlineSmall
        )

    }
}

@Composable
fun Business(
    recruiterName: String,
    onRecruiterName: (String) -> Unit,
    recruiterLastname: String,
    onRecruiterLastname: (String) -> Unit,
    companyName: String,
    onCompanyName: (String) -> Unit,
    isSelectedOptionBusinessValid: Boolean,
    onSelectedOptionBusinessValidChange: (Boolean) -> Unit
) {
    Text(
        text = "Business Sign Up",
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

        RecruiterName(
            name = recruiterName,
            onNameChange = { onRecruiterName(it) }
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        RecruiterLastname(
            lastname = recruiterLastname,
            onLastNameChange = { onRecruiterLastname(it) }
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        CompanyName(
            name = companyName,
            onNameChange = { onCompanyName(it) }
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        BusinessArea(
            isSelectedOptionValid = isSelectedOptionBusinessValid,
            onSelectedOptionValidChange = { onSelectedOptionBusinessValidChange(it) }
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessArea(
    isSelectedOptionValid: Boolean,
    onSelectedOptionValidChange: (Boolean) -> Unit
) {
    val optionsBusinessArea = listOf("Info", "Secre") // TODO: Change this
    var selectedOptionBussinesArea by remember { mutableStateOf("Select option") }
    var expandedBusinessArea by remember { mutableStateOf(false) }

    Text(
        modifier = Modifier.padding(start = ThemeDefaults.textPadding),
        text = "Business Area",
        style = MaterialTheme.typography.headlineSmall,
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
                        onSelectedOptionValidChange( selectedOptionBussinesArea != "Select option" )
                    }
                )
            }
        }
    }
}

@Composable
fun CompanyName(
    name: String,
    onNameChange: (String) -> Unit
) {
    var showSupportingText by remember { mutableStateOf(false) }

    Text(
        modifier = Modifier.padding(start = ThemeDefaults.textPadding),
        text = "Company Name",
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ThemeDefaults.textFieldPadding),
        value = name,
        onValueChange = {
            onNameChange(it)
            showSupportingText = it.isEmpty()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text ),
        maxLines = 1,
        singleLine =  true,
        supportingText = { if (showSupportingText) Text("Company Name is required") }
    )
}

@Composable
fun RecruiterLastname(
    lastname: String,
    onLastNameChange: (String) -> Unit
) {
    var showSupportingText by remember { mutableStateOf(false) }

    Text(
        modifier = Modifier.padding(start = ThemeDefaults.textPadding),
        text = "Recruiter Last Name",
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ThemeDefaults.textFieldPadding),
        value = lastname,
        onValueChange = {
            onLastNameChange(it)
            showSupportingText = it.isEmpty()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text ),
        maxLines = 1,
        singleLine = true,
        supportingText = { if (showSupportingText) Text("Recruiter last Name is required") }
    )
}

@Composable
fun RecruiterName(
    name: String,
    onNameChange: (String) -> Unit
) {
    var showSupportingText by remember { mutableStateOf(false) }

    Text(
        modifier = Modifier.padding(start = ThemeDefaults.textPadding),
        text = "Recruiter Name",
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ThemeDefaults.textFieldPadding),
        value = name,
        onValueChange = {
            onNameChange(it)
            showSupportingText = it.isEmpty()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text ),
        maxLines = 1,
        singleLine = true,
        supportingText = { if (showSupportingText) Text("Recruiter Name is required") }
    )
}


@Composable
fun Student(
    name: String,
    onNameChange: (String) -> Unit,
    lastname: String,
    onLastNameChange: (String) -> Unit,
    isSelectedOptionCollegeValid: Boolean,
    onSelectedOptionCollegeValidChange: (Boolean) -> Unit,
    isSelectedOptionDegreeValid: Boolean,
    onSelectedOptionDegreeValidChange: (Boolean) -> Unit

) {
    Text(
        text = "Student Sign Up",
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

        StudentName(
            name = name,
            onNameChange = { onNameChange(it) }
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        StudentLastName(
            lastname = lastname,
            onLastNameChange = { onLastNameChange(it) }
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        College(
            isSelectedOptionValid = isSelectedOptionCollegeValid,
            onSelectedOptionValidChange = { onSelectedOptionCollegeValidChange(it) }
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        Degree(
            isSelectedOptionValid = isSelectedOptionDegreeValid,
            onSelectedOptionValidChange = { onSelectedOptionDegreeValidChange(it) }
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Degree( // TODO
    isSelectedOptionValid: Boolean,
    onSelectedOptionValidChange : (Boolean) -> Unit
) {
    var expandedDegree by remember { mutableStateOf(false) }
    val optionsDegree = listOf("College", "University") // TODO: Change this
    var selectedOptionDegree by remember { mutableStateOf("Select option") }

    Text(
        modifier = Modifier.padding(start = ThemeDefaults.textPadding),
        text = "Academic degree",
        style = MaterialTheme.typography.headlineSmall,
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
                        onSelectedOptionValidChange( selectedOptionDegree != "Select option" )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun College( // TODO
    isSelectedOptionValid: Boolean,
    onSelectedOptionValidChange : (Boolean) -> Unit
) {
    var expandedCollege by remember { mutableStateOf(false) }
    val optionsCollege = listOf("Tecnico", "Academico") // TODO: Change this
    var selectedOptionCollege by remember { mutableStateOf("Select option") }

    Text(
        modifier = Modifier.padding(start = ThemeDefaults.textPadding),
        text = "College",
        style = MaterialTheme.typography.headlineSmall,
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
                        onSelectedOptionValidChange( selectedOptionCollege != "Select option" )
                    }
                )
            }
        }
    }
}


@Composable
fun StudentName(
    name: String,
    onNameChange: (String) -> Unit
) {
    var showSupportingText by remember { mutableStateOf(false) }

    Text(
        modifier = Modifier.padding(start = ThemeDefaults.textPadding),
        text = "Name",
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ThemeDefaults.textFieldPadding),
        value = name,
        onValueChange = {
            onNameChange(it)
            showSupportingText = it.isEmpty()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        maxLines = 1,
        singleLine = true,
        supportingText = { if (showSupportingText) Text("Name is required") },
    )
}

@Composable
fun StudentLastName(
    lastname: String,
    onLastNameChange: (String) -> Unit
) {
    var showSupportingText by remember { mutableStateOf(false) }

    Text(
        modifier = Modifier.padding(start = ThemeDefaults.textPadding),
        text = "Last Name",
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ThemeDefaults.textFieldPadding),
        value = lastname,
        onValueChange = {
            onLastNameChange(it)
            showSupportingText = it.isEmpty()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        maxLines = 1,
        singleLine = true,
        supportingText = { if (showSupportingText) Text("Last Name is required") },
    )
}