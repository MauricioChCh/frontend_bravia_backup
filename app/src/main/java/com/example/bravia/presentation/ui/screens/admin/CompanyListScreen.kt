package com.example.bravia.presentation.ui.screens.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.ui.components.PullToRefreshLazyColumn
import com.example.bravia.presentation.ui.components.cardsAnditems.CompanyCard
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.ui.theme.ThemeHelper
import com.example.bravia.presentation.viewmodel.AdminState
import com.example.bravia.presentation.viewmodel.AdminViewModel

import kotlinx.coroutines.launch

@Composable
fun CompanyListScreen(
    navController: NavController,
    viewModel: AdminViewModel
) {
    val TAG = "CompanyListScreen"

    var searchText by remember { mutableStateOf("") }
    val companies by viewModel.companies.collectAsState()
    val adminState by viewModel.adminState.collectAsState()

    var isRefreshing by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.fetchAllCompanies()
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(ThemeHelper.colors.primary)
                .padding(top = 36.dp, bottom = 12.dp, start = 18.dp, end = 10.dp)
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    viewModel.searchCompanies(it) // Llamar a la función de búsqueda
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search companies...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = RoundedCornerShape(ThemeDefaults.searchFieldShape),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = ThemeHelper.colors.onPrimary,
                    unfocusedContainerColor = ThemeHelper.colors.background,
                    focusedIndicatorColor = ThemeHelper.colors.primary,
                    unfocusedIndicatorColor = ThemeHelper.colors.onBackground
                )
            )
        }

        when (adminState) {
            AdminState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Loading companies...")
                }
            }

            AdminState.Success -> {
                if (companies.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No companies available",
                            style = ThemeHelper.typography.bodyLarge,
                            color = ThemeHelper.colors.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(ThemeDefaults.screenPadding)
                            .padding(bottom = 40.dp)
                    ) {
                        PullToRefreshLazyColumn(
                            items = companies,
                            content = { company ->
                                CompanyCard(
                                    company = company,
                                    onClick = {
                                        company.id?.let { id ->
                                            navController.navigate(NavRoutes.CompanyProfile.createRoute(id))
                                        }
                                    }
                                )
                                Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightExtraSmall))
                            },
                            isRefreshing = isRefreshing,
                            onRefresh = {
                                scope.launch {
                                    isRefreshing = true
                                    viewModel.fetchAllCompanies()
                                    isRefreshing = false
                                }
                            }
                        )
                    }
                }
            }

            AdminState.Empty -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No companies available")
                }
            }

            is AdminState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: ${(adminState as AdminState.Error).message}")
                }
            }
            //TODO: Funcion de buscar y filtrar
        }
    }
}