package com.example.bravia.presentation.ui.screens.business

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.bravia.presentation.viewmodel.BusinessViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bravia.presentation.ui.screens.shared.ErrorScreen
import com.example.bravia.presentation.ui.screens.shared.LoadingScreen
import com.example.bravia.presentation.viewmodel.BusinessState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessEditProfileScreen(
    businessViewModel: BusinessViewModel,
    navController: NavController,
    paddingValues: PaddingValues
) {

    val company by businessViewModel.company.collectAsState()
    val cities by businessViewModel.cities.collectAsState()
    val countries by businessViewModel.countries.collectAsState()
    val businessAreas by businessViewModel.businessAreas.collectAsState()
    val tags by businessViewModel.tags.collectAsState()

    var address by remember { mutableStateOf("") }


    when (val state = businessViewModel.businessState.collectAsState().value) {
        is BusinessState.Success -> {
            // The internship data is already handled by the collectAsState above
        }
        is BusinessState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetry = { /*businessViewModel.selectedBusinessInternshipById(internshipId)*/ } // TODO: Implement retry logic
            )
        }
        is BusinessState.Loading -> {
            LoadingScreen()
        }

        BusinessState.Empty -> TODO()
    }

    when (company)  {
        else -> LoadingScreen()
    }

    LaunchedEffect(Unit) {
        businessViewModel.fetchCompanyById()
    }

    var isMarked by remember { mutableStateOf(false) }
    var edit by remember { mutableStateOf(true) }

    if (company == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No company found",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        }
        return
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {Text("Internship Details")},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
//                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.secondaryContainer)
            )
        }
    ) {innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding() * 5)
                .background(MaterialTheme.colorScheme.background)
        ) {
            company.let { company ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                ) {
                    Row (
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "image", // TODO : Placeholder for company logo
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    FieldEditText(
                        label = "Company Name",
                        value = company!!.name.toString(),
                        onValueChange = { company.name = it },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    FieldEditText(
                        label = "Company Description",
                        value = company.description.toString(),
                        onValueChange = { company.description = it },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    ComboBoxContainer(
                        label = "Cities",
                        text = company.location?.city?.name.toString(),
                        items = cities.map { it.name },
                        onValueChange = {  value ->
                            val selectedCity = cities.find { it.name == value }
                            if (selectedCity != null) {
                                company.location?.city = selectedCity
                            }
                        },
                        isEditing = edit
                    )

                    ComboBoxContainer(
                        label = "Countries",
                        text = company.location?.country?.name.toString(),
                        items = countries.map { it.name },
                        onValueChange = { value ->
                            val selectedCountry = countries.find { it.name == value }
                            if (selectedCountry != null) {
                                company.location?.country = selectedCountry
                            }
                        },
                        isEditing = edit
                    )

                    FieldEditText(
                        label = "Address",
                        value = address,
                        onValueChange = { address = it },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    // TODO: Add items for business areas, tags.

                    FieldEditText(
                        label = "Recruiter First Name",
                        value = company.firstName.toString(),
                        onValueChange = { company.firstName = it },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    FieldEditText(
                        label = "Recruiter Last Name",
                        value = company.lastName.toString(),
                        onValueChange = { company.lastName = it },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    FieldEditText(
                        label = "Recruiter Email",
                        value = company.email.toString(),
                        onValueChange = { company.email = it },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                }
            }
        }
    }
}




@Composable
fun FieldEditText(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        textStyle = MaterialTheme.typography.bodyLarge,
        singleLine = true,
        shape = MaterialTheme.shapes.medium
    )

}
