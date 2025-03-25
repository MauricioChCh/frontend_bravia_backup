package com.example.bravia.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.bravia.presentation.ui.theme.ThemeDefaults


@Composable
fun SavedIntershipsScreen (
    navController: NavController,
    paddingValues: PaddingValues
){
    var searchText by remember { mutableStateOf("") }

    MainLayout(paddingValues = paddingValues) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ThemeDefaults.screenPadding)
        ) {
            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))
            Text(
                text = "Mis pasantías",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

            if (searchText.isEmpty()) {
                // Mostrar mensaje si no hay pasantías guardadas
                Text(
                    text = "No tienes pasantías guardadas",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                // Mostrar lista de pasantías guardadas

            }
        }
    }

}