package com.example.bravia.presentation.ui.screens.business

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import com.example.bravia.domain.model.Location
import com.example.bravia.domain.model.UpdateInternship
import com.example.bravia.presentation.ui.screens.admin.ProfileSection
import com.example.bravia.presentation.ui.screens.shared.ErrorScreen
import com.example.bravia.presentation.ui.screens.shared.LoadingScreen
import com.example.bravia.presentation.viewmodel.BusinessState

@SuppressLint("MutableCollectionMutableState")
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

    val selectedBusinessAreas = remember { mutableStateOf(company!!.businessAreas?.map { it }!!.toMutableSet()) }
    val selectedTags = remember { mutableStateOf(company!!.tags?.map { it }!!.toMutableSet()) }


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
        businessViewModel.fetchCities()
        businessViewModel.fetchCountries()
        businessViewModel.fetchBusinessAreas()
        businessViewModel.fetchTags()
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

                    BoxContainer(
                        label = "Company Name",
                        text = company!!.name.toString(),
                        onValueChange = { company.name = it },
                        edit = edit,
                    )

                    BoxContainer(
                        label = "Company Description",
                        text = company.description.toString(),
                        edit = edit,
                        onValueChange = { company.description = it },
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

                    BoxContainer(
                        label = "Address",
                        text = address,
                        edit = edit,
                        onValueChange = { address = it },
                    )

                    Text(
                        text = "Select Business Areas",
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        businessAreas.forEach { area ->
                            SelectableChip(
                                label = area.name,
                                isSelected = selectedBusinessAreas.value.contains(area),
                                onClick = {
                                    if (selectedBusinessAreas.value.contains(area)) {
                                        selectedBusinessAreas.value.remove(area)
                                    } else {
                                        selectedBusinessAreas.value.add(area)
                                    }
                                }
                            )
                        }
                    }


                    Text(
                        text = "Select Tags",
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        tags.forEach { tag ->
                            SelectableChip(
                                label = tag.name,
                                isSelected = selectedTags.value.contains(tag),
                                onClick = {
                                    if (selectedTags.value.contains(tag)) {
                                        selectedTags.value.remove(tag)
                                    } else {
                                        selectedTags.value.add(tag)
                                    }
                                }
                            )
                        }
                    }


//                    BoxContainer(
//                        label = "Recruiter First Name",
//                        text = company.firstName.toString(),
//                        edit = edit,
//                        onValueChange = { company.firstName = it },
//                    )
//
//                    BoxContainer(
//                        label = "Recruiter Last Name",
//                        text = company.lastName.toString(),
//                        edit = edit,
//                        onValueChange = { company.lastName = it },
//                    )
//
//                    BoxContainer(
//                        label = "Recruiter Email",
//                        text = company.email.toString(),
//                        edit = edit,
//                        onValueChange = { company.email = it },
//                    )

                    ProfileSection("") {
                        Column (
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Button(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                onClick = {
                                    businessViewModel.updateCompany(
                                        company,
                                        selectedBusinessAreas.value.toList(),
                                        selectedTags.value.toList(),
                                        Location(
                                            id = 0L,
                                            address = address,
                                            city = cities.first(),
                                            country = countries.first()
                                        )
                                    )
                                }

                            ) {
                                Text(text = "Saved", style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                }
            }
        }
    }
}







@Composable
fun SelectableChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .clickable { onClick() },
        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(50),
        tonalElevation = 4.dp,
        border = if (isSelected) null else ButtonDefaults.outlinedButtonBorder
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        )
    }
}

