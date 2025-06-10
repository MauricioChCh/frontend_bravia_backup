package com.example.bravia.presentation.ui.screens.student

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.domain.model.Internship
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.ui.components.cardsAnditems.InternshipCard
import com.example.bravia.presentation.ui.components.PullToRefreshLazyColumn
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.viewmodel.InternshipViewModel
import kotlinx.coroutines.delay
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
        if (bookmarkedInternships.isEmpty() && appliedInternships.isEmpty()) {
            viewModel.loadBookmarkedInternships()
            viewModel.loadAppliedInternships()
        }

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
                                        .background(MaterialTheme.colorScheme.onPrimary)
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
    var isRefreshing by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(ThemeDefaults.screenPadding)
                .padding(bottom = 40.dp),


        ) {
            PullToRefreshLazyColumn(
                items = internships,
                content = { internship ->  // Aquí pasamos la función de renderizado
                    InternshipCard(
                        internship = internship,
                        initialBookmarked = internship.isMarked,
                        iconA = Icons.Default.Bookmark,
                        iconB = Icons.Default.BookmarkBorder,
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