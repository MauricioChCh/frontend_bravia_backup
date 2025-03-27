// MainActivity.kt
package com.example.bravia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bravia.data.datasource.InternshipDataSourceImpl
import com.example.bravia.data.mapper.InternshipMapper
import com.example.bravia.data.repository.InternshipRepositoryImpl
import com.example.bravia.navigation.NavGraph
import com.example.bravia.presentation.factory.InternshipViewModelFactory
import com.example.bravia.presentation.ui.components.BottomNavigationBar

import com.example.bravia.presentation.viewmodel.InternshipViewModel
import com.example.bravia.presentation.viewmodel.LoginViewModel
import com.example.bravia.presentation.viewmodel.SignupViewModel
import com.example.studentapp.presentation.ui.theme.BravIATheme

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
        setContent {
            BravIATheme {
                //MainScreen(internshipViewModel)
                MainScreen(signUpViewModel, internshipViewModel, loginViewModel)

            }
        }
    }
}

@Composable
fun MainScreen(singUpviewModel: SignupViewModel, internshipViewModel: InternshipViewModel, loginViewModel: LoginViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold (
        bottomBar = {


            if (currentRoute != "login"){
                BottomNavigationBar(navController = navController)
            }
        }
    ) { paddingValues ->
        NavGraph(
            navController = navController,
            paddingValues = paddingValues,
            internshipViewModel = internshipViewModel,
            signUpViewModel = singUpviewModel,
            loginViewModel = loginViewModel
        )
    }
}


/*
class MainActivity : ComponentActivity() {

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
        setContent {
            BravIATheme {
                MainScreen(internshipViewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: InternshipViewModel) {
    val navController = rememberNavController()

    // Obtener la ruta actual para determinar si mostrar la barra de navegación
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Determinar si la ruta actual es una pantalla de detalle
    val isDetailScreen = currentRoute?.startsWith("internshipDetail") == true

    Scaffold(
        bottomBar = {
            // Solo mostrar la barra de navegación si NO estamos en una pantalla de detalle
            if (!isDetailScreen) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { paddingValues ->
        NavGraph(
            navController = navController,
            paddingValues = paddingValues,
            viewModel = viewModel
        )
    }
}
*/
