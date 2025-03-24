package com.example.bravia.presentation.ui.layout


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bravia.R

/**
 * MainLayout es un componente que proporciona la estructura de diseño estándar para la aplicación.
 *
 * Este componente establece un marco visual consistente en todas las pantallas con:
 * - Un encabezado prominente que muestra el nombre de la aplicación con semántica de accesibilidad adecuada
 * - Un subtítulo descriptivo que refuerza el propósito de la aplicación
 * - Un área de contenido designada para elementos de UI específicos de la pantalla
 *
 * @param paddingValues Valores de relleno que se aplicarán al diseño, típicamente del Scaffold
 * @param content El contenido específico de la pantalla que se mostrará dentro de este diseño
 */
@Composable
fun MainLayout(
    paddingValues: PaddingValues, content: @Composable () -> Unit
) {
    // Obtener recursos de strings antes de usarlos en semántica
    val appName = stringResource(id = R.string.app_name)
    val appTitle = stringResource(id = R.string.app_title)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            // Sección de encabezado de la aplicación con marca principal
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                shadowElevation = 4.dp
            ) {
                Text(
                    text = appName,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp)
                        .semantics { heading() })
            }

            // Sección de subtítulo de la aplicación
            Text(
                text = appTitle,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )

            // Contenido específico de la pantalla
            content()
        }
    }
}