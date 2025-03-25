// navigation/NavGraph.kt
package com.example.bravia.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.compose.ui.unit.dp
import com.example.bravia.presentation.navigation.BottomNavBar
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.ui.screens.HomeScreen

import com.example.bravia.presentation.ui.screens.InternshipDetailScreen
import com.example.bravia.presentation.ui.screens.InternshipScreen
import com.example.bravia.presentation.ui.screens.ProfileScreen
import com.example.bravia.presentation.ui.screens.SavedInternshipsScreen

import com.example.bravia.presentation.viewmodel.InternshipViewModel



@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: InternshipViewModel
) {
    NavHost(navController = navController, startDestination = BottomNavBar.Routes.HOME) {
        // Pantalla principal
        composable(
            route = BottomNavBar.Routes.HOME,
            arguments = emptyList()
        ) {
            HomeScreen(
                navController = navController,
                paddingValues = paddingValues,
                viewModel = viewModel
            )
        }

        // Pantalla de interships
        composable(
            route = BottomNavBar.Routes.INTERNSHIP,
            arguments = emptyList()
        ) {
//            InternshipScreen(
//                navController = navController,
//                paddingValues = paddingValues,
//                viewModel = viewModel
//            )
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
                paddingValues = PaddingValues(0.dp),
                viewModel = viewModel
            )
        }

        // Pantalla de guardados
        composable(
            route = BottomNavBar.Routes.SAVED,
            arguments = emptyList()
        ) {
            SavedInternshipsScreen(
                navController = navController,
                paddingValues = paddingValues,
                viewModel = viewModel
            )
        }

        // Pantalla de perfil
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