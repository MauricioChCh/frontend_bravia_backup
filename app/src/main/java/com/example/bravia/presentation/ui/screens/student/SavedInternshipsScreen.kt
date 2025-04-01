package com.example.bravia.presentation.ui.screens.student

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.domain.model.Internship
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.ui.components.InternshipCard
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.viewmodel.InternshipViewModel
import kotlinx.coroutines.launch

@Composable
fun SavedInternshipsScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: InternshipViewModel
) {
    val bookmarkedInternships by viewModel.bookmarkedInternships.collectAsState()
    val appliedInternships by viewModel.appliedInternships.collectAsState()

    val tabs = listOf("Applied", "Saved")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.loadBookmarkedInternships()
        viewModel.loadAppliedInternships()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(top = 36.dp, bottom = 12.dp, start = 18.dp, end = 10.dp), // Reducido el padding vertical
        ) {
            Column {
                Text(
                    text = "My Internships",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    tabs.forEachIndexed { index, title ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(end = 26.dp)
                                .clickable {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                }
                        ) {
                            Text(
                                text = title,
                                color = if (pagerState.currentPage == index)
                                    MaterialTheme.colorScheme.onPrimary
                                else
                                    MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                                fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Normal
                            )

                            // Only show underline for selected tab
                            if (pagerState.currentPage == index) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Box(
                                    modifier = Modifier
                                        .width(50.dp)
                                        .height(1.5.dp)
                                        .background(MaterialTheme.colorScheme.onSurface)
                                )
                                // Añadir espacio de 6dp después del subrayado
                                Spacer(modifier = Modifier.height(6.dp))
                            } else {
                                // Mantener la alineación con el tab seleccionado
                                Spacer(modifier = Modifier.height(8.dp)) // 4dp + 2dp + 6dp
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Contenido de cada tab
        HorizontalPager(state = pagerState) { page ->
            val internships = if (page == 0) appliedInternships else bookmarkedInternships
            InternshipList(
                internships = internships,
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun InternshipList(
    internships: List<Internship>,
    navController: NavController,
    viewModel: InternshipViewModel
) {
    if (internships.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No internships available",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(internships) { internship ->
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