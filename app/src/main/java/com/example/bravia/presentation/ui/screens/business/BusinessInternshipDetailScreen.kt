package com.example.bravia.presentation.ui.screens.business

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.domain.model.UpdateInternship
import com.example.bravia.presentation.ui.screens.shared.ErrorScreen
import com.example.bravia.presentation.ui.screens.shared.LoadingScreen
import com.example.bravia.presentation.ui.screens.student.ProfileSection
import com.example.bravia.presentation.viewmodel.BusinessState
import com.example.bravia.presentation.viewmodel.BusinessViewModel
import java.text.SimpleDateFormat
import java.util.Locale


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessInternshipDetailScreen(
    navController: NavController,
    internshipId: Long,
    paddingValues: PaddingValues,
    viewModel: BusinessViewModel
) {

    val internship by viewModel.internship.collectAsState()
    val modalities by viewModel.modalities.collectAsState()
    val locations by viewModel.locations.collectAsState()

    when (val state = viewModel.businessState.collectAsState().value) {
        is BusinessState.Success -> {
            // The internship data is already handled by the collectAsState above
        }
        is BusinessState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetry = { viewModel.selectedBusinessInternshipById(internshipId) }
            )
        }
        is BusinessState.Loading -> {
            LoadingScreen()
        }

        BusinessState.Empty -> TODO()
    }

    when (internship)  {
        else -> LoadingScreen()
    }

    LaunchedEffect(internshipId) {
        viewModel.selectedBusinessInternshipById(internshipId)
        viewModel.fetchModalities()
        viewModel.fetchLocations()
    }

    var isMarked by remember { mutableStateOf(false) }
    var edit by remember { mutableStateOf(false) }

    LaunchedEffect(internship) {
        isMarked = if (internship != null) {
            internship?.isMarked == true
        } else {
            false // Reset if internship is null
        }
    }

    if (internship == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No internship found",
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
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding() * 5)
                .background(MaterialTheme.colorScheme.background)
        ) {
            internship?.let { internship ->
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
                                text = internship.company.first().toString(),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    ProfileSection(title = "") {
                        Row(
                            modifier = Modifier.padding(bottom = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = internship.company,
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Spacer(modifier = Modifier.weight(1f)) // Empuja el IconButton a la derecha

                            IconButton(
                                modifier = Modifier.padding(start = 16.dp),
                                onClick = {
                                    isMarked = !isMarked
                                    viewModel.bookmarkInternship (internship.id, isMarked) // TODO: Cambiar por una variable
                                },
                            ) {
                                Icon(
                                    imageVector = if (isMarked) Icons.Default.Star else Icons.Default.StarBorder,
                                    contentDescription = if (isMarked) "Remove starred" else "Add starred",
                                    tint = if (isMarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }



                    ProfileSection(title = "Internship Title") {

                        BoxContainer( text = internship.title, edit = edit,
                            onValueChange = {
                                internship.title = it
                            }
                        )

                    }

                    ProfileSection(title = "Internship Details") {
                        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                        val formattedDate = dateFormat.format(internship.publicationDate)

                        BoxContainer(text = "Description: ${internship.description}", edit = edit, label = "Description",
                            onValueChange = {
                                internship.description = it
                            }
                        )

                        ComboBoxContainer("Modality", items = modalities.map { it.name }, text = internship.modality, isEditing = edit,
                            onValueChange = {
                                internship.modality = it
                            }
                        )

                        BoxContainer("Activities: ${internship.activities}", edit = edit, label = "Activities",
                            onValueChange = {
                                internship.activities = it
                            }
                        )
                        BoxContainer("Requirements: ${internship.requirements}", edit = edit, label = "Requirements",
                            onValueChange = {
                                internship.requirements = it
                            }
                        )
                        BoxContainer("Schedule: ${internship.schedule}",   edit = edit, label = "Schedule",
                            onValueChange = {
                                internship.schedule = it
                            }
                        )
                        BoxContainer("Duration: ${internship.duration}", edit = edit, label = "Duration",
                            onValueChange = {
                                internship.duration = it
                            }
                        )

                        BoxContainer("Publication Date: $formattedDate", edit = false, label = "Publication Date")

                        BoxContainer("Salary: ${internship.salary}", edit = edit, label = "Salary",
                            keyboard = KeyboardOptions(keyboardType = KeyboardType.Number),
                            onValueChange = {
                                internship.salary = it.toDoubleOrNull() ?: 0.0
                            }
                        )

                        ComboBoxContainer(label = "Location", items = locations.map { it.toShortString() }, text = internship.city + ", " + internship.country, isEditing = edit,
                            onValueChange = { value ->
                                val selectedLocation = locations.find { it.toShortString() == value }
                                if (selectedLocation != null) {
                                    internship.city = selectedLocation.city.name
                                    internship.country = selectedLocation.country.name
                                }
                            }
                        )

                        BoxContainer("Link: ${internship.link}", edit = edit, label = "Link",
                            onValueChange = {
                                internship.link = it
                            }
                        )
                    }

                    // Section for buttons
                    ProfileSection("") {
                        Column(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Button(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                onClick = {
                                    if (edit) {
                                        edit =  false
                                    viewModel.updateInternship(
                                        UpdateInternship(
                                            id = internship.id,
                                            company = internship.company,
                                            location = locations.firstOrNull { it.city.name == internship.city && it.country.name == internship.country }?.id ?: 0L,
                                            title = internship.title,
                                            description = internship.description,
                                            imageUrl = internship.imageUrl,
                                            publicationDate = internship.publicationDate,
                                            duration = internship.duration,
                                            salary = internship.salary,
                                            modality = internship.modality,
                                            schedule = internship.schedule,
                                            requirements = internship.requirements,
                                            activities = internship.activities,
                                            link = internship.link,
                                        )
                                    )
                                    } else {
                                        edit = true
                                    }
                                }

                            ) {
                                Text(text = if (edit) "Save Changes" else "Edit Internship", style = MaterialTheme.typography.bodyLarge)
                            }
                            Button(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                onClick = {
//                                    viewModel.viewParticipants(internship.id) // TODO: Cambiar por una variable
//                                    navController.navigate("")
                                }
                            ) {
                                Text(text = "View Participants", style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComboBoxContainer(
    label : String,
    text: String,
    items : List<String>,
    isEditing: Boolean,
    onValueChange: (String) -> Unit = {}
){
    var expanded = remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(text) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .animateContentSize(),
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 2.dp,
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
    ) {
        if (isEditing) {
            ExposedDropdownMenuBox(
                expanded = expanded.value,
                onExpandedChange = { expanded.value = !expanded.value }
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth().padding(8.dp)
                        .clickable { expanded.value = true }
                        .menuAnchor(),
                    value = items.find { it == selectedItem } ?: label,
                    onValueChange = {
                        selectedItem = it
                        onValueChange(it)
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                        cursorColor = MaterialTheme.colorScheme.primary
                    ),
                    readOnly = true,
                    isError = false,
                    label = { Text(label) },
                    trailingIcon = {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    },
                )
                ExposedDropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    items.forEach { item ->
                        DropdownMenuItem(
                            onClick = {
                                selectedItem = item
                                onValueChange(item)
                                expanded.value = false
                            },
                            text = {
                                Text(item)
                            }
                        )
                    }
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$label: $text",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

    }



}

@Composable
fun BoxContainer(
    text: String = "",
    edit: Boolean = false,
    label: String = "",
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    keyboard : KeyboardOptions = KeyboardOptions.Default
) {
    val animatedVisibility by remember { mutableStateOf(true) }
    val inputValue = remember(text) {
        mutableStateOf(
            if (":" in text) text.substringAfter(":").trim() else text
        )
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .animateContentSize(),
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 2.dp,
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
    ) {
        AnimatedContent(targetState = edit, label = "EditAnimation") { isEditing ->
            if (isEditing) {
                Column(modifier = Modifier.padding(12.dp)) {
                    OutlinedTextField(
                        value = inputValue.value,
                        onValueChange = {
                            inputValue.value = it
                            onValueChange(it)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(label) },
                        placeholder = { Text("Ingrese $label...") },
                        textStyle = MaterialTheme.typography.bodyLarge,
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                            cursorColor = MaterialTheme.colorScheme.primary
                        ),
                        trailingIcon = {
                            Icon(Icons.Default.Edit, contentDescription = "Editar")
                        },
                        keyboardOptions = keyboard,
                    )
                }
            } else {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}





