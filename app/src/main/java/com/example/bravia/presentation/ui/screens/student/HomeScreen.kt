package com.example.bravia.presentation.ui.screens.student

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.ui.components.cardsAnditems.InternshipCard
import com.example.bravia.presentation.ui.components.PullToRefreshLazyColumn
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.viewmodel.InternshipViewModel
import kotlinx.coroutines.launch
import com.example.bravia.presentation.ui.theme.ThemeHelper as Theme

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

    var isRefreshing by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    // Realizar búsqueda cuando cambia el texto
    LaunchedEffect(Unit) {
        // Cargar solo si es necesario (primera vez o datos vacíos)
        if (internships.isEmpty()) {
            viewModel.findAllInternships()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Theme.colors.primary)
                .padding(
                    top = 36.dp,
                    bottom = 12.dp,
                    start = 18.dp,
                    end = 10.dp
                ), // Reducido el padding vertical
        ) {
            // Campo de búsqueda
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search interships...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = RoundedCornerShape(ThemeDefaults.searchFieldShape),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Theme.colors.primary,
                    unfocusedContainerColor = Theme.colors.primary
                )
            )

            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))
        }
        // Lista de pasantías
        if (internships.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No internships available",
                    style = Theme.typography.bodyLarge,
                    color = Theme.colors.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(ThemeDefaults.screenPadding)
                    .padding(bottom = 40.dp)
            ) {
                PullToRefreshLazyColumn(
                    items = internships,
                    content = { internship ->  // Aquí pasamos la función de renderizado
                        InternshipCard(
                            internship = internship,
                            initialBookmarked = internship.isBookmarked,
                            onBookmarkChange = { isBookmarked ->
                                viewModel.bookmarkInternship(internship.id, isBookmarked)
                            },
                            onClick = {
                                navController.navigate(
                                    NavRoutes.InternshipDetail.createRoute(internship.id)
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightExtraSmall))
                    },
                    isRefreshing = isRefreshing,
                    onRefresh = {
                        scope.launch {
                            isRefreshing = true
                            //Recargar los datos
                            viewModel.findAllInternships(forceRefresh = true)
                            isRefreshing = false
                        }
                    }
                )
            }
        }
    }
}


