package com.example.bravia

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bravia.navigation.NavGraph
import com.example.bravia.presentation.navigation.NavigationManager
import com.example.bravia.presentation.ui.theme.BravIATheme
import com.example.bravia.presentation.viewmodel.BusinessViewModel
import com.example.bravia.presentation.viewmodel.InternshipViewModel
import com.example.bravia.presentation.viewmodel.LoginState
import com.example.bravia.presentation.viewmodel.LoginViewModel
import com.example.bravia.presentation.viewmodel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.example.bravia.presentation.navigation.BottomNavBar
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bravia.presentation.viewmodel.ThemeViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Reinicia la aplicación volviendo a la actividad principal
    fun restartApp() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel: ThemeViewModel = hiltViewModel()
            val themeState by themeViewModel.themeState.collectAsState()
            BravIATheme(themeState = themeState){
                val navController = rememberNavController()
                val navigationManager = NavigationManager()

                // ViewModels
                val loginViewModel: LoginViewModel = hiltViewModel()
                val internshipViewModel: InternshipViewModel = hiltViewModel()
                val signUpViewModel: SignupViewModel = hiltViewModel()
                val businessViewModel: BusinessViewModel = hiltViewModel()

                // Estado de la sesión del usuario
                val userSession by loginViewModel.userSession.collectAsState()
                val loginState by loginViewModel.loginState.collectAsState()

                // Manejar la navegación después del login exitoso
                LaunchedEffect(loginState) {
                    if (loginState is LoginState.Success) {
                        val destination = (loginState as LoginState.Success).destination
                        navController.navigate(destination) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                }

                Scaffold(
                    bottomBar = {
                        // Solo mostrar bottom bar si el usuario está autenticado
                        userSession?.let { session ->
                            val currentRoute =
                                navController.currentBackStackEntry?.destination?.route
                            if (currentRoute != null && BottomNavBar.isBottomNavRoute(currentRoute)) {
                                BottomNavigationBar(
                                    navController = navController,
                                    userRole = session.role
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    NavGraph(
                        navController = navController,
                        paddingValues = paddingValues,
                        internshipViewModel = internshipViewModel,
                        signUpViewModel = signUpViewModel,
                        loginViewModel = loginViewModel,
                        businessViewModel = businessViewModel,
                        onLogout = { restartApp() } // ← Pasa el callback aquí
                    )
                }
            }
        }
    }
}


@Composable
fun BottomNavigationBar(
    navController: androidx.navigation.NavHostController,
    userRole: com.example.bravia.domain.model.UserRole
) {
    val items = BottomNavBar.getItemsForRole(userRole)

    // Esta es la clave: usar collectAsState() para hacer la ruta reactiva
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(stringResource(item.title)) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

