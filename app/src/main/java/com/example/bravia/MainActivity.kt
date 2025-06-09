package com.example.bravia

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
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Inicialización de los ViewModel
//    private val signUpViewModel: SignupViewModel by viewModels ()
//    private val loginViewModel : LoginViewModel by viewModels()
//    private val internshipViewModel: InternshipViewModel by viewModels()
//    private val businessViewModel: BusinessViewModel by viewModels()
//


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BravIATheme {
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
                        businessViewModel = businessViewModel
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

//@Composable
//fun MainScreen(
//    singUpViewModel: SignupViewModel,
//    internshipViewModel: InternshipViewModel,
//    loginViewModel: LoginViewModel,
//    businessViewModel: BusinessViewModel
//) {
//    val navController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//    Log.d("ThemeDebug", "Primary color: ${MaterialTheme.colorScheme.primary}")
//
//    Scaffold (
//        bottomBar = {
//            when (currentRoute) {
//                NavRoutes.Login.ROUTE,
//                NavRoutes.Start.ROUTE,
//                NavRoutes.LoginSaved.ROUTE,
//                NavRoutes.SignIn.ROUTE,
//                NavRoutes.SignUp.ROUTE,
//                NavRoutes.InterestsSignUp.ROUTE,
//                NavRoutes.ProfileSignUp.ROUTE,
//                NavRoutes.InternshipDetail.ROUTE -> {} // Do nothing (oculta el bottom bar)
//                else -> BottomNavigationBar(navController = navController, currentRoute)
//            }
//        }
//    ) { paddingValues ->
//        NavGraph(
//            navController = navController,
//            paddingValues = paddingValues,
//            internshipViewModel = internshipViewModel,
//            signUpViewModel = singUpViewModel,
//            loginViewModel = loginViewModel,
//            businessViewModel = businessViewModel
//        )
//    }
//}


