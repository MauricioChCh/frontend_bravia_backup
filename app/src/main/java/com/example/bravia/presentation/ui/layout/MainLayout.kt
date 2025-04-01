package com.example.bravia.presentation.ui.layout


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


/**
 * MainLayout es un componente que proporciona la estructura de diseño estándar para la aplicación.
 *
 * @param paddingValues Valores de relleno que se aplicarán al diseño, típicamente del Scaffold
 * @param showHeader Si se debe mostrar el encabezado con el logo de la aplicación
 * @param title Título opcional para mostrar en la parte superior del contenido
 * @param content El contenido específico de la pantalla que se mostrará dentro de este diseño
 */
@Composable
fun MainLayout(
    paddingValues: PaddingValues,
    showHeader: Boolean = true,
    title: String? = null,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
//            // Logo de la aplicación (solo si showHeader es true)
//            if (showHeader) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp, vertical = 12.dp),
//                    contentAlignment = Alignment.CenterStart
//                ) {
//                    Text(
//                        text = "eportuIA",
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color(0xFF0A0A0A)
//                    )
//                }
//            }
//
//            // Título de la sección (si se proporciona)
//            if (title != null) {
//                Text(
//                    text = title,
//                    style = MaterialTheme.typography.headlineMedium,
//                    fontWeight = FontWeight.Bold,
//                    color = MaterialTheme.colorScheme.onBackground,
//                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
//                )
//            }

            // Contenido específico de la pantalla
            content()
        }
    }
}