package com.example.bravia.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.compose.ui.unit.dp

import com.example.bravia.presentation.ui.screens.InternshipDetailScreen
import com.example.bravia.presentation.ui.screens.InternshipScreen
import com.example.bravia.presentation.ui.screens.ProfileScreen
import com.example.bravia.presentation.ui.screens.SavedIntershipsScreen
import com.example.studentapp.presentation.ui.screens.HomeScreen

/**
 * NavGraph es una función componible que configura el gráfico de navegación para la aplicación.
 * Define todas las rutas de navegación disponibles y las asocia con sus respectivas pantallas.
 *
 * @param navController El NavHostController utilizado para la navegación entre pantallas
 * @param paddingValues Los valores de relleno para aplicar a las pantallas, típicamente del Scaffold
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(navController = navController, startDestination = BottomNavBar.Routes.HOME) {
        // Pantalla principal - punto de entrada principal de la aplicación
        composable(
            route = BottomNavBar.Routes.HOME,
            arguments = emptyList()
        ) {
            HomeScreen(
                navController = navController,
                paddingValues = paddingValues
            )
        }

        // Pantalla de interships
        composable(
            route = BottomNavBar.Routes.INTERNSHIP,
            arguments = emptyList()
        ) {
            InternshipScreen(
                navController = navController,
                paddingValues = paddingValues
            )
        }

        // Pantalla detalle de internship
        composable(
            route = NavRoutes.InternshipDetail.ROUTE,
            arguments = listOf(
                navArgument(NavRoutes.InternshipDetail.ARG_INTERNSHIP_ID) {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val internshipId = backStackEntry.arguments?.getLong(NavRoutes.InternshipDetail.ARG_INTERNSHIP_ID) ?: -1L
            InternshipDetailScreen(
                navController = navController,
                internshipId = internshipId,
                paddingValues = PaddingValues(0.dp) // Cambiado a 0.dp para ignorar el padding del Scaffold principal
            )
        }

        // Pantalla de guardados
        composable(
            route = BottomNavBar.Routes.SAVED,
            arguments = emptyList()
        ) {
            SavedIntershipsScreen(
                navController = navController,
                paddingValues = paddingValues
            )
        }

        // Pantalla de perfil - muestra y permite editar el perfil del usuario
        composable(
            route = BottomNavBar.Routes.PROFILE,
            arguments = emptyList()
        ) {
            ProfileScreen(
                navController = navController,
                paddingValues = paddingValues
            )
        }
    }
}

