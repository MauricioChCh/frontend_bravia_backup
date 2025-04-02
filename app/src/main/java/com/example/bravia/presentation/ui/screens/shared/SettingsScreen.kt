import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.bravia.presentation.ui.components.ContrastDropdown
import com.example.bravia.presentation.ui.components.LanguageDropdown
import com.example.bravia.presentation.ui.components.SettingsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    var searchText by remember { mutableStateOf("") }
    var selectedLanguage by remember { mutableStateOf("English") }
    var selectedContrast by remember { mutableStateOf("Medium") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { /* Navegar hacia atrás */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            // Barra de búsqueda
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(24.dp)),
                placeholder = { Text("Search config") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    IconButton(onClick = { searchText = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear")
                    }
                },
                singleLine = true,

            )

            // Lista de configuraciones
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    SettingsItem(
                        title = "Color Scheme",
                        onClick = { /* Acción para esquema de color */ },
                        trailingContent = {
                            Icon(
                                imageVector = Icons.Default.LightMode,
                                contentDescription = "Light Mode",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    )
                }

                item {
                    SettingsItem(
                        title = "Contrast",
                        onClick = { /* No es necesario, el dropdown maneja la interacción */ },
                        trailingContent = {
                            ContrastDropdown(
                                selectedContrast = selectedContrast,
                                onContrastSelected = { selectedContrast = it }
                            )
                        }
                    )
                }

                item {
                    SettingsItem(
                        title = "Language",
                        onClick = { /* No es necesario, el dropdown maneja la interacción */ },
                        trailingContent = {
                            LanguageDropdown(
                                selectedLanguage = selectedLanguage,
                                onLanguageSelected = { selectedLanguage = it }
                            )
                        }
                    )
                }

                item {
                    SettingsItem(
                        title = "Preferences",
                        onClick = { /* Acción para preferencias */ }
                    )
                }

                item {
                    SettingsItem(
                        title = "Activity log",
                        onClick = { /* Acción para registro de actividad */ }
                    )
                }

                item {
                    SettingsItem(
                        title = "Subscription & payment method",
                        onClick = { /* Acción para suscripción y pagos */ }
                    )
                }

                item {
                    SettingsItem(
                        title = "Account management",
                        onClick = { /* Acción para gestión de cuenta */ }
                    )
                }

                // Espacio para el botón de logout
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }

                // Botón de logout
                item {
                    Button(
                        onClick = { /* Acción para cerrar sesión */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                            contentColor = MaterialTheme.colorScheme.onErrorContainer
                        )
                    ) {
                        Text("Logout")
                    }
                }

                // Espacio al final
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

