package com.example.bravia.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.ui.components.InternshipCard
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.viewmodel.InternshipViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: InternshipViewModel
) {
    // Estado para el campo de búsqueda
    var searchText by remember { mutableStateOf("") }

    // Obtener la lista de pasantías del ViewModel
    val internships by viewModel.internshipList.collectAsState()

    // Realizar búsqueda cuando cambia el texto
    LaunchedEffect(searchText) {
        viewModel.searchInternships(searchText)
    }

    MainLayout(paddingValues = paddingValues) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ThemeDefaults.screenPadding)
        ) {
            // Campo de búsqueda
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar pasantías...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = RoundedCornerShape(ThemeDefaults.searchFieldShape),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                )
            )

            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

            // Lista de pasantías
            if (internships.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay pasantías disponibles",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(count = internships.size) { index ->
                        val internship = internships[index]
                        InternshipCard(
                            internship = internship,
                            initialBookmarked = internship.isBookmarked,
                            onBookmarkChange = { isBookmarked ->
                                viewModel.bookmarkInternship(internship.id, isBookmarked)
                            },
                            onClick = {
                                navController.navigate(NavRoutes.InternshipDetail.createRoute(internship.id))
                            }
                        )
                        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightExtraSmall))
                    }
                }
            }
        }
    }
}