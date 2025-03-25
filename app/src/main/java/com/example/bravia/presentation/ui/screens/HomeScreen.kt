package com.example.studentapp.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bravia.data.datasource.InternshipsProvider
import com.example.bravia.navigation.NavRoutes
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.bravia.presentation.ui.components.InternshipCard
import com.example.bravia.presentation.ui.theme.ThemeDefaults


@Composable
fun HomeScreen(
    navController: NavController,
    paddingValues: PaddingValues
) {
    // Estado para el campo de búsqueda
    var searchText by remember { mutableStateOf("") }

    // Obtener la lista de pasantías del proveedor
    val internships = InternshipsProvider.findAllInternships()

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
                    // Usar colores del tema
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                )
            )

            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

            // Título de sección
            Text(
                text = "Mis pasantías",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
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
                    items(internships) { internship ->
                        InternshipCard(
                            internship = internship,
                            onClick = {
                                navController.navigate(NavRoutes.InternshipDetail.createRoute(internship.id))
                            }
                        )
                        Spacer(modifier = Modifier.height(ThemeDefaults.cardSpacerHeight))
                    }
                }
            }
        }
    }
}