package com.example.bravia.presentation.ui.screens.student

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.presentation.ui.layout.MainLayout

@Composable
fun AppliedInternshipsScreen(
    navController: NavController,
    paddingValues: PaddingValues
) {
    var searchText by remember { mutableStateOf("") }

    // Para demostración, usamos la misma lista de pasantías
    // En una implementación real, tendrías una lista filtrada de pasantías guardadas
    //val savedInternships = InternshipProvider.findAllInternships().take(2)

    MainLayout(
        paddingValues = paddingValues,
        showHeader = true
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            // Campo de búsqueda
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar en aplicadas...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Título de sección
            Text(
                text = "Pasantías aplicadas",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de pasantías aplicadas
//            if (savedInternships.isEmpty()) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(1f),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Text(
//                            text = "No has aplicado a ninguna pasantía",
//                            style = MaterialTheme.typography.bodyLarge,
//                            color = MaterialTheme.colorScheme.onSurfaceVariant,
//                            textAlign = TextAlign.Center
//                        )
//
//                        Spacer(modifier = Modifier.height(8.dp))
//
//                        Text(
//                            text = "Aplica a pasantías para verlas aquí",
//                            style = MaterialTheme.typography.bodyMedium,
//                            color = MaterialTheme.colorScheme.onSurfaceVariant,
//                            textAlign = TextAlign.Center
//                        )
//                    }
//                }
//            } else {
//                LazyColumn(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    items(savedInternships) { internship ->
//                        InternshipCard(
//                            internship = internship,
//                            onClick = {
//                                navController.navigate(NavRoutes.InternshipDetail.createRoute(internship.id))
//                            }
//                        )
//                        Spacer(modifier = Modifier.height(12.dp))
//                    }
//                }
//            }
        }
    }
}

