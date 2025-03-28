// presentation/ui/screens/SavedInternshipsScreen.kt
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.ui.components.InternshipCard
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.viewmodel.InternshipViewModel

@Composable
fun SavedInternshipsScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: InternshipViewModel
) {
    // Obtener las pasantías guardadas
    val bookmarkedInternships by viewModel.bookmarkedInternships.collectAsState()

    // Cargar las pasantías guardadas al entrar en la pantalla
    LaunchedEffect(Unit) {
        viewModel.loadBookmarkedInternships()
    }

    MainLayout(paddingValues = paddingValues) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ThemeDefaults.screenPadding)
        ) {
            Text(
                text = "Saved Internships",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (bookmarkedInternships.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No saved internships",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(bookmarkedInternships) { internship ->
                        InternshipCard(
                            internship = internship,
                            initialBookmarked = true,
                            onBookmarkChange = { isBookmarked ->
                                viewModel.bookmarkInternship(internship.id, isBookmarked)
                            },
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