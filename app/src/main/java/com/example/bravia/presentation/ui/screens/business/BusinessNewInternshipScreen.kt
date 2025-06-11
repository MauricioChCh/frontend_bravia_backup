package com.example.bravia.presentation.ui.screens.business


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.domain.model.NewInternship
import com.example.bravia.presentation.viewmodel.BusinessViewModel
import java.util.Date
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessNewInternshipScreen (
  navController: NavController,
  businessViewModel: BusinessViewModel,
) {
    val company by businessViewModel.company.collectAsState()
    val locations by businessViewModel.locations.collectAsState()
    val modalities by businessViewModel.modalities.collectAsState()

    LaunchedEffect(Unit) {
        businessViewModel.fetchLocations()
        businessViewModel.fetchModalities()
    }

    var selectedLocationId by remember { mutableStateOf<Long?>(null) }
    var expandedLocation by remember { mutableStateOf(false) }

    var selectedModality by remember { mutableStateOf<Long?>(null) }
    var expandedModality by remember { mutableStateOf(false) }

    var imageUrl by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var salary by remember { mutableStateOf("") }
    var schedule by remember { mutableStateOf("") }
    var requirements by remember { mutableStateOf("") }
    var activities by remember { mutableStateOf("") }
    var link by remember { mutableStateOf("") }

    var enableButton by remember { mutableStateOf(true) }

    LaunchedEffect(
        title, imageUrl, duration, salary, selectedModality, schedule, requirements, activities, link, selectedLocationId
    ) {
        enableButton = title.isNotBlank() &&
                imageUrl.isNotBlank() &&
                duration.isNotBlank() &&
                salary.isNotBlank() &&
                selectedModality != null &&
                schedule.isNotBlank() &&
                requirements.isNotBlank() &&
                activities.isNotBlank() &&
                link.isNotBlank() &&
                selectedLocationId != null
    }

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(top = 25.dp, bottom = 30.dp),
    ) {
        Text(
            text = "New Internship",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp, vertical = 40.dp)

        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 48.dp, top = 160.dp)
            .verticalScroll(rememberScrollState()),

        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {


        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text("Company") },
                value = company?.name ?: "Unknown Company",
                onValueChange = {},
                readOnly = true
            )

            ExposedDropdownMenuBox(
                expanded = expandedLocation,
                onExpandedChange = { expandedLocation = !expandedLocation }
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedLocation = true }
                        .menuAnchor(),
                    value = locations.find { it.id == selectedLocationId }?.toShortString() ?: "Select Location",
                    onValueChange = {},
                    readOnly = true,
                    isError = false,
                    label = { Text("Location") },
                    trailingIcon = {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    },
                )
                ExposedDropdownMenu(
                    expanded = expandedLocation,
                    onDismissRequest = { expandedLocation = false }
                ) {
                    locations.forEach { location ->
                        DropdownMenuItem(
                            onClick = {
                                selectedLocationId = location.id
                                expandedLocation = false
                            },
                            text = {
                                Text("${location.country.name}, ${location.city.name}")
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text("Image URL") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = duration,
                onValueChange = { duration = it },
                label = { Text("Duration") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = salary,
                onValueChange = { input ->
                    if (input.all { it.isDigit() || it == '.' }) {
                        salary = input
                    }
                },
                label = { Text("Salary") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            ExposedDropdownMenuBox(
                expanded = expandedModality,
                onExpandedChange = { expandedModality = !expandedModality }
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedModality = true }
                        .menuAnchor(),
                    value = modalities.find { it.id == selectedModality }?.name ?: "Select Modality",
                    onValueChange = {},
                    readOnly = true,
                    isError = false,
                    label = { Text("Modality") },
                    trailingIcon = {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    },
                )
                ExposedDropdownMenu(
                    expanded = expandedModality,
                    onDismissRequest = { expandedModality = false }
                ) {
                    modalities.forEach { modality ->
                        DropdownMenuItem(
                            onClick = {
                                selectedModality = modality.id
                                expandedModality = false
                            },
                            text = {
                                Text(modality.name)
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = schedule,
                onValueChange = { schedule = it },
                label = { Text("Schedule") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = requirements,
                onValueChange = { requirements = it },
                label = { Text("Requirements") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = activities,
                onValueChange = { activities = it },
                label = { Text("Activities") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = link,
                onValueChange = { link = it },
                label = { Text("Link") },
                modifier = Modifier.fillMaxWidth(),
            )
            Button(
                onClick = {
                    val newInternship = NewInternship(
                        company = company?.id ?: 0L,
                        location = selectedLocationId!!, // TODO: this has to be a variable of the location selected
                        title = title,
                        description = description,
                        imageUrl = imageUrl,
                        publicationDate = Date(),
                        duration = duration,
                        salary = salary.toDoubleOrNull() ?: 0.0,
                        modality = selectedModality.toString(),
                        schedule = schedule,
                        requirements = requirements,
                        activities = activities,
                        link = link
                    )
                    businessViewModel.addInternship(newInternship)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = enableButton

            ) {
                Text("Publish Internship")
            }
        }
    }
}