package com.example.bravia.presentation.ui.screens.business

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.presentation.ui.screens.shared.ErrorScreen
import com.example.bravia.presentation.ui.screens.shared.LoadingScreen
import com.example.bravia.presentation.viewmodel.BusinessState
import com.example.bravia.presentation.viewmodel.BusinessViewModel
import kotlinx.coroutines.channels.ticker


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessInternshipDetailScreen(
    navController: NavController,
    internshipId: Long,
    paddingValues: PaddingValues,
    viewModel: BusinessViewModel
) {
//    val state by viewModel.internshipState.collectAsState()
//
//    when (state) {
//        is BusinessState.Loading -> LoadingScreen()
//        is BusinessState.Error -> ErrorScreen(
//            message = "The internship could not be loaded",
//            onRetry = { viewModel.selectBusinessInternshipById(1, internshipId) } // TODO: Cambiar por una variable
//        )
//        is BusinessState.Success -> {
//            // Handle success state
//        }
//        else -> ErrorScreen(message = "No data found")
//    }
//
//    LaunchedEffect(internshipId) {
//        viewModel.selectBusinessInternshipById(1, internshipId) // TODO: Cambiar por una variable
//    }
//
//    val internship by viewModel.selectedInternship.collectAsState()
//
//    var isMarked by remember { mutableStateOf(false) }
//
//    LaunchedEffect(internship) {
//        internship?.let {
//            isMarked = it.isMarked
//        }
//    }
//
//    if (internship == null) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = "No internship found",
//                style = MaterialTheme.typography.bodyLarge,
//                color = MaterialTheme.colorScheme.error
//            )
//        }
//        return
//    }
//
//    Scaffold (
//        topBar = {
//            TopAppBar(
//                title = {Text("Internship Details")},
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.Default.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.background
//                )
//            )
//        }
//    ) { innerPadding ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .background(MaterialTheme.colorScheme.background)
//        ) {
//            internship?.let { internship ->
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp)
//                        .verticalScroll(rememberScrollState())
//                ) {
//                    Text(
//                        text = "About the internship",
//                        style = MaterialTheme.typography.headlineMedium,
//                        fontWeight = FontWeight.Bold
//                    )
//
//                    IconButton(
//                        onClick = {
//                            isMarked = !isMarked
//                            viewModel.markInternship(1 , internship.id, isMarked) // TODO: Cambiar por una variable
//                        },
//                    ) {
//                        Icon(
//                            imageVector = if (isMarked) Icons.Default.Star else Icons.Default.StarBorder,
//                            contentDescription = if (isMarked) "Remove starred" else "Add starred",
//                            tint = if (isMarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
//                        )
//                    }
//
//                    Box(
//                        modifier = Modifier
//                            .size(20.dp)
//                            .clip(CircleShape)
//                            .background(MaterialTheme.colorScheme.primary),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(
//                            text = internship.company.first().toString(),
//                            style = MaterialTheme.typography.bodyLarge,
//                            color = MaterialTheme.colorScheme.onSurface
//                        )
//                    }
//
//                }
//            }
//        }
//    }
//
}
//    val state by viewModel.internshipState.collectAsState()
//    viewModel.internshipState.collectAsState()
//
//    when(state) {
//        is BusinessState.Loading -> LoadingScreen()
//        is BusinessState.Error -> ErrorScreen(
//            message = "The internship could not be loaded",
//            onRetry = { viewModel.selectBusinessInternshipById(internshipId) }
//        )
//        is BusinessState.Success -> {
//
//        }
//        else -> ErrorScreen(message = "No data found")
//    }
//
//    LaunchedEffect(internshipId) {
//        viewModel.selectBusinessInternshipById(internshipId)
//    }
//
//    val internship by viewModel.selectedInternship.collectAsState()
//
//    var isMarked by remember { mutableStateOf(false) }
//    LaunchedEffect(internship) {
//        internship?.let {
//            isMarked = it.isMarked
//        }
//    }
//
//    if (internship == null) {
//        Box (
//           modifier = Modifier
//               .fillMaxSize()
//                .padding(paddingValues),
//           contentAlignment = Alignment.Center
//
//        ) {
//            Text (
//                text = "No internship found",
//                style = MaterialTheme.typography.bodyLarge,
//                color = MaterialTheme.colorScheme.error
//            )
//        }
//        return
//    }
//
//    Scaffold (
//        topBar = {
//            TopAppBar(
//               title = { Text(text = "Internship Details") },
//                navigationIcon = {
//                     IconButton(onClick = { navController.popBackStack() }) {
//                          Icon(
//                              imageVector =  Icons.Default.ArrowBack,
//                              contentDescription = "Back")
//                     }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.background
//                )
//            )
//        }
//    ) { innerPadding ->
//        Box (
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .background(MaterialTheme.colorScheme.background)
//        ) {
//            internship?.let { internship ->
//                Column (
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp)
//                        .verticalScroll(rememberScrollState())
//                ) {
//                    Text(
//                        text = "About the internship",
//                        style = MaterialTheme.typography.headlineMedium,
//                        fontWeight = FontWeight.Bold
//                    )
//
//                    IconButton(
//                        onClick = {
//                            isMarked = !isMarked
//                            viewModel.markInternship(internship.id, isMarked)
//                        },
//                    ) {
//                        Icon(
//                            imageVector = if (isMarked) Icons.Default.Star else Icons.Default.StarBorder,
//                            contentDescription = if (isMarked) "Remove starred" else "Add starred",
//                            tint = if (isMarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
//                        )
//                    }
//
//                    Box (
//                        modifier = Modifier
//                            .size(20.dp)
//                            .clip(CircleShape)
//                            .background(MaterialTheme.colorScheme.primary),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(
//                            text = internship.company.first().toString(),
//                            style = MaterialTheme.typography.bodyLarge,
//                            color = MaterialTheme.colorScheme.onSurface
//                        )
//                    }
//
//                }
//            }
//
//        }
//    }
//}