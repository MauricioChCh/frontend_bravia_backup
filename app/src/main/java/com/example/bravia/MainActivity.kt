package com.example.bravia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.bravia.navigation.NavGraph
import com.example.bravia.presentation.ui.components.BottomNavigationBar

import com.example.studentapp.presentation.ui.theme.StudentAppTheme

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
            StudentAppTheme {
                // Inicializar el NavController
                val navController = rememberNavController()

                // Configurar la pantalla principal con el NavController
                StudentAppScreen(navController = navController)
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
fun StudentAppScreen(navController: androidx.navigation.NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) { paddingValues ->
            NavGraph(
                navController = navController,
                paddingValues = paddingValues
            )
        }
    }
}

