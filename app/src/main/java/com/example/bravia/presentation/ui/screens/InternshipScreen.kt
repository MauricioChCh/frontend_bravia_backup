package com.example.bravia.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.data.datasource.InternshipsProvider
import com.example.bravia.navigation.NavRoutes
import com.example.bravia.presentation.ui.layout.MainLayout

@Composable
fun InternshipScreen(
    navController: NavController,
    paddingValues: PaddingValues
) {
    // Obtener la lista de pasantías
    val internships = InternshipsProvider.findAllInternships()

    MainLayout(paddingValues = paddingValues) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (internships.isEmpty()) {
                // Mostrar mensaje si no hay pasantías
                Box(
                    modifier = Modifier.fillMaxSize(),
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
                // Mostrar lista de pasantías
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = internships,
                        key = { internship -> internship.id }
                    ) { internship ->
                        InternshipCard(
                            internship = internship,
                            onClick = {
                                navController.navigate(NavRoutes.InternshipDetail.createRoute(internship.id))
                            }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}