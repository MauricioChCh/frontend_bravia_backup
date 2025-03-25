package com.example.bravia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bravia.navigation.BottomNavBar
import com.example.bravia.navigation.NavGraph
import com.example.bravia.navigation.NavRoutes
import com.example.bravia.presentation.ui.components.BottomNavigationBar

import com.example.studentapp.presentation.ui.theme.BravIATheme

/**
 * Punto de entrada principal de la aplicación de estudiantes.
 *
 * Esta actividad configura la interfaz de usuario principal utilizando Jetpack Compose
 * y establece la navegación entre pantallas.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BravIATheme {

                // Configurar la pantalla principal con el NavController
                MainScreen()
            }
        }
    }
}

/**
 * Pantalla principal que integra el sistema de navegación.
 *
 * @param navController Controlador para gestionar la navegación entre pantallas
 */
@Composable
fun MainScreen() {
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
            paddingValues = paddingValues
        )
    }
}


