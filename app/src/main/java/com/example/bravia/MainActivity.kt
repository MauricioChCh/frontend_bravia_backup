package com.example.bravia

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bravia.navigation.NavGraph
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.ui.components.BottomNavigationBar
import com.example.bravia.presentation.ui.theme.BravIATheme
import com.example.bravia.presentation.viewmodel.BusinessViewModel
import com.example.bravia.presentation.viewmodel.InternshipViewModel
import com.example.bravia.presentation.viewmodel.LoginViewModel
import com.example.bravia.presentation.viewmodel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Inicialización de los ViewModel
    private val signUpViewModel: SignupViewModel by viewModels ()
    private val loginViewModel : LoginViewModel by viewModels()
    private val internshipViewModel: InternshipViewModel by viewModels()
    private val businessViewModel: BusinessViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            BravIATheme {
                MainScreen(
                    singUpViewModel = signUpViewModel,
                    internshipViewModel = internshipViewModel,
                    loginViewModel = loginViewModel,
                    businessViewModel = businessViewModel
                )
            }
        }
    }
}


@Composable
fun MainScreen(
    singUpViewModel: SignupViewModel,
    internshipViewModel: InternshipViewModel,
    loginViewModel: LoginViewModel,
    businessViewModel: BusinessViewModel
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Log.d("ThemeDebug", "Primary color: ${MaterialTheme.colorScheme.primary}")

    Scaffold (
        bottomBar = {
            when (currentRoute) {
                NavRoutes.Login.ROUTE,
                NavRoutes.Start.ROUTE,
                NavRoutes.LoginSaved.ROUTE,
                NavRoutes.SignIn.ROUTE,
                NavRoutes.SignUp.ROUTE,
                NavRoutes.InterestsSignUp.ROUTE,
                NavRoutes.ProfileSignUp.ROUTE,
                NavRoutes.InternshipDetail.ROUTE -> {} // Do nothing (oculta el bottom bar)
                else -> BottomNavigationBar(navController = navController, currentRoute)
            }
        }
    ) { paddingValues ->
        NavGraph(
            navController = navController,
            paddingValues = paddingValues,
            internshipViewModel = internshipViewModel,
            signUpViewModel = singUpViewModel,
            loginViewModel = loginViewModel,
            businessViewModel = businessViewModel
        )
    }
}


