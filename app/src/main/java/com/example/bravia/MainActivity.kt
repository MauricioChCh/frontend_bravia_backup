package com.example.bravia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bravia.data.datasource.InternshipDataSourceImpl
import com.example.bravia.data.mapper.InternshipMapper
import com.example.bravia.data.repository.InternshipRepositoryImpl
import com.example.bravia.navigation.NavGraph
import com.example.bravia.presentation.factory.InternshipViewModelFactory
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.ui.components.BottomNavigationBar
import com.example.bravia.presentation.ui.theme.BravIATheme
import com.example.bravia.presentation.viewmodel.InternshipViewModel
import com.example.bravia.presentation.viewmodel.LoginViewModel
import com.example.bravia.presentation.viewmodel.SignupViewModel

class MainActivity : ComponentActivity() {

    private val signUpViewModel : SignupViewModel by viewModels()
    private val loginViewModel : LoginViewModel by viewModels()
    private val internshipViewModel: InternshipViewModel by viewModels {
        // Crear el mapper
        val internshipMapper = InternshipMapper()

        // Crear la fuente de datos
        val dataSource = InternshipDataSourceImpl()

        // Crear el repositorio
        val repository = InternshipRepositoryImpl(dataSource, internshipMapper)

        // Crear la fábrica del ViewModel
        InternshipViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BravIATheme {
                //MainScreen(internshipViewModel)
                MainScreen(signUpViewModel, internshipViewModel, loginViewModel)

            }
        }
    }
}

@Composable
fun MainScreen(singUpViewModel: SignupViewModel, internshipViewModel: InternshipViewModel, loginViewModel: LoginViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

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
                else -> BottomNavigationBar(navController = navController)
            }
        }
    ) { paddingValues ->
        NavGraph(
            navController = navController,
            paddingValues = paddingValues,
            internshipViewModel = internshipViewModel,
            signUpViewModel = singUpViewModel,
            loginViewModel = loginViewModel
        )
    }
}


//Joshua
//class MainActivity : ComponentActivity() {
//
//    private val signUpViewModel : SignupViewModel by viewModels {
//        // Crear el mapper
//        val interestMapper = InterestMapper()
//
//        // Crear la fuente de datos
//        val dataSource = InterestDataSourceImpl()
//
//        // Crear el repositorio
//        val repository = InterestRepositoryImpl(dataSource, interestMapper)
//
//        // Crear la fábrica del ViewModel
//        SignupViewModelFactory(repository)
//    }
//    private val internshipViewModel: InternshipViewModel by viewModels {
//        // Crear el mapper
//        val internshipMapper = InternshipMapper()
//
//        // Crear la fuente de datos
//        val dataSource = InternshipDataSourceImpl()
//
//        // Crear el repositorio
//        val repository = InternshipRepositoryImpl(dataSource, internshipMapper)
//
//        // Crear la fábrica del ViewModel
//        InternshipViewModelFactory(repository)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            BravIATheme {
//                //MainScreen(internshipViewModel)
//                MainScreen(signUpViewModel, internshipViewModel)
//            }
//        }
//    }
//}
//Mau
//@Composable
//fun MainScreen(singUpviewModel: SignupViewModel, internshipViewModel: InternshipViewModel) {
//    val navController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//
//    Scaffold (
//        bottomBar = {
//
//
//            if (currentRoute != "signup" && currentRoute?.contains("profileSignup") == false && currentRoute != "interestsSignup") {
//                BottomNavigationBar(navController = navController)
//            }
//        }
//    ) { paddingValues ->
//        NavGraph(
//            navController = navController,
//            paddingValues = paddingValues,
//            internshipViewModel = internshipViewModel,
//            signUpViewModel = singUpviewModel
//        )
//    }
//}

