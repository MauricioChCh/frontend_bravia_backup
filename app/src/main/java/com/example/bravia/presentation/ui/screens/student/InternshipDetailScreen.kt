package com.example.bravia.presentation.ui.screens.student

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bravia.FlutterDescriptionActivity
import com.example.bravia.domain.model.Internship
import com.example.bravia.presentation.ui.screens.shared.ErrorScreen
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.viewmodel.InternshipState
import com.example.bravia.presentation.viewmodel.InternshipViewModel
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InternshipDetailScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    internshipId: String?,
    viewModel: InternshipViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val internshipState by viewModel.internshipState.collectAsState()

    // Convertir String a Long y cargar internship
    LaunchedEffect(internshipId) {
        internshipId?.toLongOrNull()?.let { id ->
            viewModel.selectInternshipById(id)
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge))

        when (val state = internshipState) {
            is InternshipState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is InternshipState.Success -> {
                InternshipDetailContent(
                    internship = state.internship,
                    onOpenFlutterDescription = {
                        // Abrir Flutter Activity con los datos REALES
                        val intent = Intent(context, FlutterDescriptionActivity::class.java).apply {
                            putExtra(
                                FlutterDescriptionActivity.EXTRA_INTERNSHIP_DATA,
                                Gson().toJson(state.internship) // Usar Gson para serializar el objeto completo
                            )
                        }
                        context.startActivity(intent)
                    },
                    onApply = {
                        // Lógica de aplicar - puedes usar viewModel.applyToInternship(internship.id)
                    },
                    onBookmark = {
                        // Lógica de bookmark - puedes usar viewModel.bookmarkInternship(internship.id, !internship.isMarked)
                    },
                    paddingValues = paddingValues
                )
            }
            is InternshipState.Error -> {
                ErrorScreen(
                    message = state.message,
                    onRetry = {
                        internshipId?.toLongOrNull()?.let { id ->
                            viewModel.selectInternshipById(id)
                        }
                    }
                )
            }
            InternshipState.Empty -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No se encontró la internship")
                }
            }
        }
    }
}

@Composable
private fun InternshipDetailContent(
    internship: Internship,
    onOpenFlutterDescription: () -> Unit,
    onApply: () -> Unit,
    onBookmark: () -> Unit,
    paddingValues : PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header básico en Compose
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = internship.title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = internship.company,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${internship.city}, ${internship.country}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Información básica en Compose
            item {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Información General",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        InfoRow("Duración", internship.duration)
                        InfoRow("Modalidad", internship.modality)
                        InfoRow("Horario", internship.schedule)
                        InfoRow("Salario", "$${internship.salary}")
                    }
                }
            }

            // Botón para abrir descripción detallada en Flutter
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "📋 Descripción Detallada",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Ver descripción completa, requisitos y actividades en Flutter",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = onOpenFlutterDescription,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Description,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Ver Descripción Completa")
                        }
                    }
                }
            }

            // Botones de acción en Compose
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = onApply,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Aplicar")
                    }

                    OutlinedButton(
                        onClick = onBookmark,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.BookmarkBorder,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(if (internship.isMarked) "Guardado" else "Guardar")
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}
