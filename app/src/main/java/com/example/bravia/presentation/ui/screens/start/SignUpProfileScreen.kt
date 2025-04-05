package com.example.bravia.presentation.ui.screens.start

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.domain.model.BusinessArea
import com.example.bravia.domain.model.College
import com.example.bravia.domain.model.Degree
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.ui.theme.Typography
import com.example.bravia.presentation.viewmodel.SignupViewModel


/**
 * SignUpProfileScreen is a Composable function that represents the profile screen of the sign-up process.
 * It allows users to enter their personal information based on their selected account type (Student or Business).
 *
 * @param navController The NavController used for navigation between screens.
 * @param signupViewModel The ViewModel responsible for managing the sign-up process.
 */
@Composable
fun SignUpProfileScreen (
    navController: NavController,
    signupViewModel: SignupViewModel,
) {
    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }

    var recruiterName by remember { mutableStateOf("") }
    var recruiterLastname by remember { mutableStateOf("") }
    var companyName by remember { mutableStateOf("") }

    var collegeOption by remember { mutableStateOf("") }
    var degreeOption by remember { mutableStateOf("") }
    var businessAreaOption by remember { mutableStateOf("") }

    var isSelectedOptionCollegeValid by remember { mutableStateOf(false) }
    var isSelectedOptionBusinessValid by remember { mutableStateOf(false) }
    var isSelectedOptionDegreeValid by remember { mutableStateOf(false) }

    var  selectedUserType by remember { mutableStateOf("Student") }

    Log.d("SignupViewModel", "SignUpProfileScreen email: ${signupViewModel.email}")
    Log.d("SignupViewModel", "SignUpProfileScreen password: ${signupViewModel.password}")
    Log.d("SignupViewModel", "SignUpProfileScreen confirmPassword: ${signupViewModel.confirmPassword}")
    Log.d("Account type", "SignUpProfileScreen: ${selectedUserType}")


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

        AccountType(
            selectedUserType = selectedUserType,
            onSelectedUserTypeChange = { selectedUserType = it }
        )

        if (selectedUserType == "Student") {

            Student(
                name = name,
                onNameChange = { name = it },
                lastname = lastname,
                onLastNameChange = { lastname = it },
                onCollegeOptionChange = {
                    collegeOption = it
                    Log.d("College", "SignUpProfileScreen: ${collegeOption}")
                },
                onDegreeOptionChange = {
                    degreeOption = it
                    Log.d("Degree", "SignUpProfileScreen: ${degreeOption}")
                },
                isSelectedOptionCollegeValid = isSelectedOptionCollegeValid,
                onSelectedOptionCollegeValidChange = { isSelectedOptionCollegeValid = it },
                isSelectedOptionDegreeValid = isSelectedOptionDegreeValid,
                onSelectedOptionDegreeValidChange = { isSelectedOptionDegreeValid = it },
                signupViewModel
            )

        } else if (selectedUserType == "Business") {

            Business(
                recruiterName = recruiterName,
                onRecruiterName = { recruiterName = it },
                recruiterLastname = recruiterLastname,
                onRecruiterLastname = { recruiterLastname = it },
                companyName = companyName,
                onCompanyName = { companyName = it },
                onBusinessAreaOption = {
                    businessAreaOption = it
                    Log.d("Business Area", "SignUpProfileScreen: ${businessAreaOption}")
                },
                isSelectedOptionBusinessValid = isSelectedOptionBusinessValid,
                onSelectedOptionBusinessValidChange = { isSelectedOptionBusinessValid = it },
                signupViewModel
            )

        }

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        // Button
        ContinueButton(
            typeAccount = selectedUserType,
            navController = navController,
            signupViewModel = signupViewModel,
            studentName = name,
            studentLastname = lastname,
            collegeOption = collegeOption,
            degreeOption = degreeOption,
            recruiterName = recruiterName,
            recruiterLastname = recruiterLastname,
            companyName = companyName,
            businessAreaOption = businessAreaOption,
            isSelectedOptionCollegeValid = isSelectedOptionCollegeValid,
            isSelectedOptionBusinessValid = isSelectedOptionBusinessValid,
            isSelectedOptionDegreeValid = isSelectedOptionDegreeValid
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        RedirectLogin(navController)
    }
}


/**
 * AccountType is a Composable function that displays the account type selection buttons (Student and Business).
 * It allows users to choose between the two account types.
 *
 * @param selectedUserType The currently selected user type (Student or Business).
 * @param onSelectedUserTypeChange A lambda function to handle changes in the selected user type.
 */
@Composable
fun AccountType(
    selectedUserType: String,
    onSelectedUserTypeChange: (String) -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("Student", "Business")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ThemeDefaults.textFieldPadding)
    ) {
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.displayMedium
        )

        SingleChoiceSegmentedButtonRow {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = options.size
                    ),
                    onClick = {
                        selectedIndex = index
                        onSelectedUserTypeChange(label) // Cambié para que se actualice el valor
                    },
                    selected = index == selectedIndex,
                    label = { Text(label) },
                    colors = if (index == selectedIndex) {
                        SegmentedButtonDefaults.colors( MaterialTheme.colorScheme.primary )
                    } else {
                        SegmentedButtonDefaults.colors( MaterialTheme.colorScheme.surface )
                    }
                )
            }
        }
    }
}

/**
 * RedirectLogin is a Composable function that displays a button to redirect users to the login screen.
 *
 * @param navController The NavController used for navigation between screens.
 */
@Composable
fun RedirectLogin(
    navController: NavController
) {
    Button(
        onClick = { navController.navigate("login") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
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
 * ContinueButton is a Composable function that displays a button to continue the sign-up process.
 * It navigates to different screens based on the selected account type (Student or Business).
 *
 * @param typeAccount The type of account selected (Student or Business).
 * @param navController The NavController used for navigation between screens.
 * @param signupViewModel The ViewModel responsible for managing the sign-up process.
 * @param studentName The name of the student.
 * @param studentLastname The last name of the student.
 * @param collegeOption The selected college option.
 * @param degreeOption The selected degree option.
 * @param recruiterName The name of the recruiter.
 * @param recruiterLastname The last name of the recruiter.
 * @param companyName The name of the company.
 * @param businessAreaOption The selected business area option.
 */
@Composable
fun ContinueButton(
    typeAccount: String,
    navController: NavController,
    signupViewModel: SignupViewModel,
    studentName: String,
    studentLastname: String,
    collegeOption: String,
    degreeOption: String,
    recruiterName: String,
    recruiterLastname: String,
    companyName: String,
    businessAreaOption: String,
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
                signupViewModel.onFirstNameChange(studentName)
                signupViewModel.onLastNameChange(studentLastname)
                signupViewModel.onCollegeChange(collegeOption)
                signupViewModel.onDegreeChange(degreeOption)
                navController.navigate("interestsSignup")
            } else if (typeAccount == "Business") {
                signupViewModel.onFirstNameChange(recruiterName)
                signupViewModel.onLastNameChange(recruiterLastname)
                signupViewModel.onCompanyNameChange(companyName)
                signupViewModel.onBusinessAreaChange(businessAreaOption)
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

/**
 * Business is a Composable function that displays the business-related input fields for the sign-up process.
 * It allows users to enter their recruiter name, last name, company name, and select a business area.
 *
 * @param recruiterName The name of the recruiter.
 * @param onRecruiterName A lambda function to handle changes in the recruiter name.
 * @param recruiterLastname The last name of the recruiter.
 * @param onRecruiterLastname A lambda function to handle changes in the recruiter last name.
 * @param companyName The name of the company.
 * @param onCompanyName A lambda function to handle changes in the company name.
 * @param onBusinessAreaOption A lambda function to handle changes in the selected business area option.
 * @param isSelectedOptionBusinessValid A boolean indicating whether a valid business area option is selected.
 * @param onSelectedOptionBusinessValidChange A lambda function to handle changes in the validity of the selected business area option.
 * @param signupViewModel The ViewModel responsible for managing the sign-up process.
 */
@Composable
fun Business(
    recruiterName: String,
    onRecruiterName: (String) -> Unit,
    recruiterLastname: String,
    onRecruiterLastname: (String) -> Unit,
    companyName: String,
    onCompanyName: (String) -> Unit,
    onBusinessAreaOption: (String) -> Unit,
    isSelectedOptionBusinessValid: Boolean,
    onSelectedOptionBusinessValidChange: (Boolean) -> Unit,
    signupViewModel: SignupViewModel
) {

    var optionsBusinessArea by remember { mutableStateOf(emptyList<BusinessArea>()) }

    signupViewModel.findAllBusinessAreas()

    val businessAreas = signupViewModel.listOfBusinessArea.collectAsState()

    LaunchedEffect(businessAreas.value) {
        optionsBusinessArea = businessAreas.value
    }

    Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

    Card(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(0.5.dp, Color.Black),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface ),
        elevation = CardDefaults.cardElevation(ThemeDefaults.cardElevation)
    ) {


        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        RecruiterName(
            name = recruiterName,
            onNameChange = { onRecruiterName(it) }
        )

        RecruiterLastname(
            lastname = recruiterLastname,
            onLastNameChange = { onRecruiterLastname(it) }
        )

        CompanyName(
            name = companyName,
            onNameChange = { onCompanyName(it) }
        )

        BusinessArea(
            onSelectedOptionBusinessArea = {
                onBusinessAreaOption(it)
            },
            optionsBusinessArea = optionsBusinessArea,
            isSelectedOptionValid = isSelectedOptionBusinessValid,
            onSelectedOptionValidChange = { onSelectedOptionBusinessValidChange(it) }
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))


    }
}


/**
 * BusinessArea is a Composable function that displays a dropdown menu for selecting a business area.
 * It allows users to choose from a list of available business areas.
 *
 * @param onSelectedOptionBusinessArea A lambda function to handle changes in the selected business area option.
 * @param optionsBusinessArea A list of available business area options.
 * @param isSelectedOptionValid A boolean indicating whether a valid business area option is selected.
 * @param onSelectedOptionValidChange A lambda function to handle changes in the validity of the selected business area option.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessArea(
    onSelectedOptionBusinessArea: (String) -> Unit,
    optionsBusinessArea: List<BusinessArea>,
    isSelectedOptionValid: Boolean,
    onSelectedOptionValidChange: (Boolean) -> Unit
) {
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(ThemeDefaults.textFieldPadding)
                .clickable { expandedBusinessArea = true }
                .menuAnchor(),
            value = selectedOptionBussinesArea,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            },
            isError = !isSelectedOptionValid
        )
        ExposedDropdownMenu(
            expanded = expandedBusinessArea,
            onDismissRequest = { expandedBusinessArea = false }
        ) {
            optionsBusinessArea.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.name) },
                    onClick = {
                        selectedOptionBussinesArea = option.name
                        onSelectedOptionBusinessArea(option.name)
                        expandedBusinessArea = false
                        onSelectedOptionValidChange( selectedOptionBussinesArea != "Select option" )
                    }
                )
            }
        }
    }
}


/**
 * CompanyName is a Composable function that displays an input field for entering the company name.
 * It allows users to enter the name of their company.
 *
 * @param name The name of the company.
 * @param onNameChange A lambda function to handle changes in the company name.
 */
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


/**
 * RecruiterName is a Composable function that displays an input field for entering the recruiter's name.
 * It allows users to enter the name of the recruiter.
 *
 * @param name The name of the recruiter.
 * @param onNameChange A lambda function to handle changes in the recruiter's name.
 */
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


/**
 * RecruiterName is a Composable function that displays an input field for entering the recruiter's name.
 * It allows users to enter the name of the recruiter.
 *
 * @param name The name of the recruiter.
 * @param onNameChange A lambda function to handle changes in the recruiter's name.
 */
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


/**
 * Student is a Composable function that displays the student-related input fields for the sign-up process.
 * It allows users to enter their name, last name, and select a college and degree.
 *
 * @param name The name of the student.
 * @param onNameChange A lambda function to handle changes in the student's name.
 * @param lastname The last name of the student.
 * @param onLastNameChange A lambda function to handle changes in the student's last name.
 * @param onCollegeOptionChange A lambda function to handle changes in the selected college option.
 * @param onDegreeOptionChange A lambda function to handle changes in the selected degree option.
 * @param isSelectedOptionCollegeValid A boolean indicating whether a valid college option is selected.
 * @param onSelectedOptionCollegeValidChange A lambda function to handle changes in the validity of the selected college option.
 * @param isSelectedOptionDegreeValid A boolean indicating whether a valid degree option is selected.
 * @param onSelectedOptionDegreeValidChange A lambda function to handle changes in the validity of the selected degree option.
 * @param signupViewModel The ViewModel responsible for managing the sign-up process.
 */
@Composable
fun Student(
    name: String,
    onNameChange: (String) -> Unit,
    lastname: String,
    onLastNameChange: (String) -> Unit,
    onCollegeOptionChange: (String) -> Unit,
    onDegreeOptionChange: (String) -> Unit,
    isSelectedOptionCollegeValid: Boolean,
    onSelectedOptionCollegeValidChange: (Boolean) -> Unit,
    isSelectedOptionDegreeValid: Boolean,
    onSelectedOptionDegreeValidChange: (Boolean) -> Unit,
    signupViewModel: SignupViewModel
) {
    var optionsCollege by remember { mutableStateOf<List<College>>( emptyList() ) }
    var optionsAcademicDegree by remember { mutableStateOf<List<Degree>>( emptyList() ) }

    signupViewModel.findAllColleges()
    signupViewModel.findAllDegrees()

    val colleges = signupViewModel.listOfCollege.collectAsState()
    val degrees = signupViewModel.listOfDegree.collectAsState()

    LaunchedEffect(colleges.value, degrees.value) {
        optionsCollege = colleges.value
        optionsAcademicDegree = degrees.value
    }

    Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

    Card(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(0.5.dp, Color.Black),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface ),
        elevation = CardDefaults.cardElevation(ThemeDefaults.cardElevation)
    ) {


        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        StudentName(
            name = name,
            onNameChange = { onNameChange(it) }
        )

        StudentLastName(
            lastname = lastname,
            onLastNameChange = { onLastNameChange(it) }
        )

        College(
            onSelectedOption = {
                onCollegeOptionChange(it)
            },
            optionsCollege = optionsCollege,
            isSelectedOptionValid = isSelectedOptionCollegeValid,
            onSelectedOptionValidChange = { onSelectedOptionCollegeValidChange(it) }
        )

        Degree(
            onSelectOption = {
                onDegreeOptionChange(it)
            },
            optionsAcademicDegree = optionsAcademicDegree,
            isSelectedOptionValid = isSelectedOptionDegreeValid,
            onSelectedOptionValidChange = { onSelectedOptionDegreeValidChange(it) }
        )

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))
    }
}


/**
 * Degree is a Composable function that displays a dropdown menu for selecting an academic degree.
 * It allows users to choose from a list of available academic degrees.
 *
 * @param onSelectOption A lambda function to handle changes in the selected degree option.
 * @param optionsAcademicDegree A list of available academic degree options.
 * @param isSelectedOptionValid A boolean indicating whether a valid degree option is selected.
 * @param onSelectedOptionValidChange A lambda function to handle changes in the validity of the selected degree option.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Degree(
    onSelectOption: (String) -> Unit,
    optionsAcademicDegree: List<Degree>,
    isSelectedOptionValid: Boolean,
    onSelectedOptionValidChange : (Boolean) -> Unit
) {
    var expandedDegree by remember { mutableStateOf(false) }
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(ThemeDefaults.textFieldPadding)
                .clickable { expandedDegree = true }
                .menuAnchor(),
            value = selectedOptionDegree,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            },
            isError = !isSelectedOptionValid
        )
        ExposedDropdownMenu(
            expanded = expandedDegree,
            onDismissRequest = { expandedDegree = false }
        ) {
            optionsAcademicDegree.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.name) },
                    onClick = {
                        selectedOptionDegree = option.name
                        onSelectOption(option.name)
                        expandedDegree = false
                        onSelectedOptionValidChange( selectedOptionDegree != "Select option" )
                    }
                )
            }
        }
    }
}


/**
 * College is a Composable function that displays a dropdown menu for selecting a college.
 * It allows users to choose from a list of available colleges.
 *
 * @param onSelectedOption A lambda function to handle changes in the selected college option.
 * @param optionsCollege A list of available college options.
 * @param isSelectedOptionValid A boolean indicating whether a valid college option is selected.
 * @param onSelectedOptionValidChange A lambda function to handle changes in the validity of the selected college option.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun College(
    onSelectedOption: (String) -> Unit,
    optionsCollege: List<College>,
    isSelectedOptionValid: Boolean,
    onSelectedOptionValidChange : (Boolean) -> Unit
) {
    var expandedCollege by remember { mutableStateOf(false) }
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(ThemeDefaults.textFieldPadding)
                .clickable { expandedCollege = true }
                .menuAnchor(),
            value = selectedOptionCollege,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            },
            isError = !isSelectedOptionValid
        )
        ExposedDropdownMenu(
            expanded = expandedCollege,
            onDismissRequest = { expandedCollege = false }
        ) {
            optionsCollege.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.name) },
                    onClick = {
                        selectedOptionCollege = option.name
                        onSelectedOption(option.name)
                        expandedCollege = false
                        onSelectedOptionValidChange( selectedOptionCollege != "Select option" )
                    }
                )
            }
        }
    }
}


/**
 * StudentName is a Composable function that displays an input field for entering the student's name.
 * It allows users to enter the name of the student.
 *
 * @param name The name of the student.
 * @param onNameChange A lambda function to handle changes in the student's name.
 */
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


/**
 * StudentLastName is a Composable function that displays an input field for entering the student's last name.
 * It allows users to enter the last name of the student.
 *
 * @param lastname The last name of the student.
 * @param onLastNameChange A lambda function to handle changes in the student's last name.
 */
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