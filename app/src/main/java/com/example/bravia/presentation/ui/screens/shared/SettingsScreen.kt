import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bravia.presentation.ui.components.ContrastDropdown
import com.example.bravia.presentation.ui.components.LanguageDropdown
import com.example.bravia.presentation.ui.components.cardsAnditems.SettingsItem
import com.example.bravia.presentation.viewmodel.LoginViewModel
import android.util.Log
import androidx.navigation.NavController
import com.example.bravia.presentation.ui.theme.ContrastMode
import com.example.bravia.presentation.ui.theme.ThemeMode
import com.example.bravia.presentation.viewmodel.ThemeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateToLogin: () -> Unit, // Callback para navegar al login
    loginViewModel: LoginViewModel = hiltViewModel(),
    navController: NavController,
    themeViewModel: ThemeViewModel = hiltViewModel()
) {
    var searchText by remember { mutableStateOf("") }
    var selectedLanguage by remember { mutableStateOf("English") }
    var selectedContrast by remember { mutableStateOf("Medium") }
    var showLogoutDialog by remember { mutableStateOf(false) }

    val themeState by themeViewModel.themeState.collectAsState()

    // Observar si el logout se completó
    val logoutCompleted by loginViewModel.logoutCompleted.collectAsState()

    // Observar estados del ViewModel (deslogeara automaticamente si no hay sesion)
    val isLoading by loginViewModel.isLoading.collectAsState()
    // Redirigir cuando logoutCompleted sea true
    LaunchedEffect(logoutCompleted) {
        if (logoutCompleted) {
            onNavigateToLogin()
            loginViewModel.resetLogoutState()
        }
    }


    LaunchedEffect(Unit) {
        val isPersistentlyAuthenticated = loginViewModel.isPersistentlyAuthenticated()
        Log.d("SettingsScreen", "Initial persistent authentication check: $isPersistentlyAuthenticated")
        if (!isPersistentlyAuthenticated) {
            Log.d("SettingsScreen", "Not persistently authenticated, navigating to login")
            onNavigateToLogin()
        }
    }


    // Dialog de confirmación de logout
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Confirm Logout") },
            text = { Text("Are you sure you want to log out?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        loginViewModel.logout()
                    }
                ) {
                    Text("Logout")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
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
                    .padding(horizontal = 16.dp, vertical = 8.dp),
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
                        onClick = {
                            // Alternar entre los modos de color
                            val newMode = when (themeState.themeMode) {
                                ThemeMode.LIGHT -> ThemeMode.DARK
                                ThemeMode.DARK -> ThemeMode.SYSTEM
                                ThemeMode.SYSTEM -> ThemeMode.LIGHT

                            }
                            themeViewModel.setThemeMode(newMode )
                        },
                        trailingContent = {
                            Icon(
                                imageVector = when (themeState.themeMode) {
                                    ThemeMode.LIGHT -> Icons.Default.LightMode
                                    ThemeMode.DARK -> Icons.Default.DarkMode
                                    ThemeMode.SYSTEM -> Icons.Default.Settings

                                },
                                contentDescription = "Theme Mode",
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
                                selectedContrast = themeState.contrastMode,
                                onContrastSelected = { themeViewModel.setContrastMode(it) }
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

                // Botón de logout con loading state
                item {
                    Button(
                        onClick = { showLogoutDialog = true },
                        enabled = !isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                            contentColor = MaterialTheme.colorScheme.onErrorContainer
                        )
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(16.dp),
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Text(if (isLoading) "Logging out..." else "Logout")
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

@Composable
fun ContrastDropdown(
    selectedContrast: ContrastMode,
    onContrastSelected: (ContrastMode) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = when (selectedContrast) {
                    ContrastMode.NORMAL -> Icons.Default.Contrast
                    ContrastMode.MEDIUM -> Icons.Default.Contrast
                    ContrastMode.HIGH -> Icons.Default.Contrast
                },
                contentDescription = "Contrast Mode",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            ContrastMode.values().forEach { mode ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = when (mode) {
                                ContrastMode.NORMAL -> "Normal"
                                ContrastMode.MEDIUM -> "Medium"
                                ContrastMode.HIGH -> "High"
                            }
                        )
                    },
                    onClick = {
                        onContrastSelected(mode)
                        expanded = false
                    }
                )
            }
        }
    }
}
