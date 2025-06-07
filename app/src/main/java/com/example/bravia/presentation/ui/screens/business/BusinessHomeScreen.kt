package com.example.bravia.presentation.ui.screens.business

import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bravia.domain.model.Internship
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.ui.components.PullToRefreshLazyColumn
import com.example.bravia.presentation.ui.components.cardsAnditems.InternshipCard
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.viewmodel.BusinessViewModel
import com.example.bravia.presentation.viewmodel.InternshipViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessHomeScreen (
    navController: NavController,
    businessViewModel: BusinessViewModel
) {
//    Log.d("BusinessHomeScreen", "Recomposición de BusinessHomeScreen")
//
//    val internship by businessViewModel.selectedInternship.collectAsState()
//    val internships by businessViewModel.internshipList.collectAsState()
//    val draftInternships by businessViewModel.draftInternships.collectAsState()
//
//    var isMarked by remember { mutableStateOf(false) }
//
//    LaunchedEffect(Unit) {
//        businessViewModel.findAllBusinessOwnerInternship() // Cambia el ID según sea necesario
//        businessViewModel.loadBookmarkedInternships()
//        internship?.let {
//            isMarked = it.isMarked
//        }
//    }
//
//
//    val tabs = listOf("Published", "Draft")
//    val pagerState = rememberPagerState(pageCount = { tabs.size })
//    val coroutineScope = rememberCoroutineScope()
//
//
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        Column (
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(MaterialTheme.colorScheme.secondaryContainer),
//
//            ) {
//
//            Text(
//                text = "My internships",
//                style = MaterialTheme.typography.titleLarge,
//                color = MaterialTheme.colorScheme.onSurface,
//                fontSize = 35.sp,
//                modifier = Modifier
//                    .padding(ThemeDefaults.screenPadding)
//                    .padding(top = 40.dp)
//            )
//
//            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightSmall))
//
//            Row(
//                modifier = Modifier.fillMaxWidth().padding(start = 5.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                tabs.forEachIndexed { index, title ->
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        modifier = Modifier
//                            .padding(end = 26.dp)
//                            .clickable {
//                                coroutineScope.launch {
//                                    pagerState.animateScrollToPage(index)
//                                }
//                            }
//                    ) {
//                        Text(
//                            text = title,
//                            color = if (pagerState.currentPage == index)
//                                MaterialTheme.colorScheme.onPrimary
//                            else
//                                MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
//                            fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Normal
//                        )
//
//                        // Only show underline for selected tab
//                        if (pagerState.currentPage == index) {
//                            Spacer(modifier = Modifier.height(4.dp))
//                            Box(
//                                modifier = Modifier
//                                    .width(50.dp)
//                                    .height(1.5.dp)
//                                    .background(MaterialTheme.colorScheme.onPrimary)
//                            )
//                            // Añadir espacio de 6dp después del subrayado
//                            Spacer(modifier = Modifier.height(6.dp))
//                        } else {
//                            // Mantener la alineación con el tab seleccionado
//                            Spacer(modifier = Modifier.height(8.dp)) // 4dp + 2dp + 6dp
//                        }
//                    }
//                }
//            }
//        }
//
//        HorizontalPager(state = pagerState) { page ->
//            businessViewModel.findAllBusinessInternshipsStarred()
//            val internships = if (page == 0) internships else draftInternships
//            InternshipList(
//                navController = navController,
//                internships = internships,
//                viewModel = businessViewModel,
//                page = page,
//                textList = listOf("internships", "draft")
//            )
//        }
//    }
}
//
//
//@Composable
//fun InternshipList(
//    internships: List<Internship>,
//    navController: NavController,
//    viewModel: BusinessViewModel,
//    page: Int = 0,
//    textList: List<String>
//) {
//    var isRefreshing by remember {
//        mutableStateOf(false)
//    }
//    val scope = rememberCoroutineScope()
//
//    if (internships.isEmpty()) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 32.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = if (page == 0) "No ${textList[page]} available" else "No ${textList[page]} available",
//                style = MaterialTheme.typography.bodyLarge,
//                color = MaterialTheme.colorScheme.onSurfaceVariant,
//                textAlign = TextAlign.Center
//            )
//        }
//    } else {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(ThemeDefaults.screenPadding)
//                .padding(bottom = 40.dp),
//
//
//            ) {
//            PullToRefreshLazyColumn(
//                items = internships,
//                content = { internship ->  // Aquí pasamos la función de renderizado
//                    InternshipCard(
//                        internship = internship,
//                        initialBookmarked = internship.isMarked,
//                        iconA = Icons.Default.StarRate,
//                        iconB = Icons.Default.StarBorder,
//                        onBookmarkChange = { isBookmarked ->
//                            viewModel.markInternship(1, internship.id, isBookmarked) // TODO: Cambiar por una variable
//                        },
//                        onClick = {
//                            if (viewModel is InternshipViewModel){
//                                navController.navigate(
//                                    NavRoutes.InternshipDetail.createRoute(internship.id)
//                                )
//                            }
//                            else if (viewModel is BusinessViewModel) {
//                                navController.navigate(
//                                    NavRoutes.BusinessInternshipDetail.createRoute(internship.id)
//                                )
//                            }
//                        }
//                    )
//                    Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightExtraSmall))
//                },
//                isRefreshing = isRefreshing,
//                onRefresh = {
//                    scope.launch {
//                        isRefreshing = true
//                        viewModel.findAllBusinessOwnerInternship()
//                        isRefreshing = false
//                    }
//                }
//            )
//        }
//    }
//}