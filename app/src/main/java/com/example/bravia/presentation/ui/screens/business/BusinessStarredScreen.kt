package com.example.bravia.presentation.ui.screens.business

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.viewmodel.BusinessViewModel
import kotlinx.coroutines.launch

@Composable
fun BusinessStarredScreen(
    navController: NavController,
    businessViewModel: BusinessViewModel
) {

    val bookmarkedInternships by businessViewModel.bookmarkedInternships.collectAsState()


    LaunchedEffect(Unit) {
        businessViewModel.fetchAllBookmarkedInternships()
    }



    val tabs = listOf("Per Internship", "Students")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()



    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer),
            ) {

            Text(
                text = "Starred",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 35.sp,
                modifier = Modifier
                    .padding(ThemeDefaults.screenPadding)
                    .padding(top = 40.dp)
            )

            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightSmall))

            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 5.dp),
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

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightSmall))

        HorizontalPager(state = pagerState) { page ->
            val internships = if (page == 0) bookmarkedInternships else emptyList() // TODO: get studentInternships
            InternshipList(
                navController = navController,
                internships = internships,
                viewModel = businessViewModel,
                page = page,
                textList = listOf("internships", "students")
            )
        }

    }
}



