package com.example.bravia.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.bravia.presentation.ui.theme.ThemeDefaults




@Composable
fun InterviewScreen(
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
