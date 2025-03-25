package com.example.bravia.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.bravia.presentation.ui.theme.Typography

/**
 * Pantalla que muestra el perfil del estudiante.
 *
 * @param navController Controlador para gestionar la navegación entre pantallas
 * @param paddingValues Valores de relleno para aplicar a la pantalla
 */
@Composable
fun ProfileScreen(
    navController: NavController,
    paddingValues: PaddingValues
) {
    MainLayout(paddingValues = paddingValues) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Perfil",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Aquí puedes ver y editar tu perfil",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}