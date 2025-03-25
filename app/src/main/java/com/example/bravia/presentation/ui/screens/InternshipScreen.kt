package com.example.bravia.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.data.datasource.InternshipProvider
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.ui.components.InternshipCard
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.studentapp.presentation.ui.theme.ThemeDefaults



@Composable
fun InternshipScreen(
    navController: NavController,
    paddingValues: PaddingValues
) {


    MainLayout(paddingValues = paddingValues) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ThemeDefaults.screenPadding)
        ) {
            Text(
                text = "Interships",
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}
