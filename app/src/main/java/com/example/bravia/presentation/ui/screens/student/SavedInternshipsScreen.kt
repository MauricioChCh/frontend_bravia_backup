package com.example.bravia.presentation.ui.screens.student

import androidx.compose.foundation.background
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

    MainLayout(paddingValues = paddingValues) {
        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(vertical = 24.dp, horizontal = 16.dp),
            ) {
                Column {
                    Text(
                        text = "My Internships",
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    TabRow(
                        selectedTabIndex = pagerState.currentPage,
                        indicator = {}, // Elimina la línea inferior del tab
                        containerColor = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp), // Ajusta el padding para moverlo a la izquierda
                        divider = {} // Evita que haya una línea separadora
                    ) {
                        tabs.forEachIndexed { index, title ->
                            Tab(
                                text = {
                                    Text(
                                        title,
                                        color = if (pagerState.currentPage == index)
                                            MaterialTheme.colorScheme.onPrimary
                                        else
                                            MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                                        fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Normal
                                    )
                                },
                                selected = pagerState.currentPage == index,
                                onClick = {
                                    coroutineScope.launch { pagerState.animateScrollToPage(index) }
                                },
                                modifier = Modifier.padding(horizontal = 8.dp) // Reduce el padding horizontal para unir más las opciones
                            )
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
        LazyColumn(modifier = Modifier.fillMaxSize()) {
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
