package com.example.bravia.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.data.datasource.InternshipsProvider
import java.text.SimpleDateFormat
import java.util.Locale
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.studentapp.presentation.ui.theme.ThemeDefaults



@Composable
fun InternshipDetailScreen(
    internshipId: Long,
    navController: NavController,
    paddingValues: PaddingValues
) {
    // Obtener la pasantía por ID
    val internship = InternshipsProvider.findInternshipById(internshipId)
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    MainLayout(paddingValues = paddingValues) {
        internship?.let {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(ThemeDefaults.screenPadding)
                    .semantics { contentDescription = "Detalles de pasantía para ${internship.title}" },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {

            }
        } ?: run {

        }
    }
}