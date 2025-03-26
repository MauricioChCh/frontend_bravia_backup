// navigation/NavGraph.kt
package com.example.bravia.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bravia.presentation.navigation.BottomNavBar
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.ui.screens.HomeScreen
import com.example.bravia.presentation.ui.screens.InterestsScreen
import com.example.bravia.presentation.ui.screens.InternshipDetailScreen
import com.example.bravia.presentation.ui.screens.ProfileScreen
import com.example.bravia.presentation.ui.screens.SavedInternshipsScreen
import com.example.bravia.presentation.ui.screens.SignUpScreen
import com.example.bravia.presentation.ui.screens.SignUpProfileScreen
import com.example.bravia.presentation.viewmodel.InternshipViewModel
import com.example.bravia.presentation.viewmodel.SignupViewModel


@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    internshipViewModel: InternshipViewModel,
    signUpViewModel: SignupViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.SignUp.ROUTE
    ) {
       // Sign up Screen
       composable(
              route = NavRoutes.SignUp.ROUTE,
       ) {
            SignUpScreen(
                navController = navController,
                paddingValues = paddingValues,
                signUpViewModel = signUpViewModel
            )
       }

        // Sign up Profile Screen
        composable(
            route = NavRoutes.ProfileSignUp.ROUTE,
            arguments = listOf(
                navArgument(NavRoutes.ProfileSignUp.ARG_EMAIL) {
                    type = NavType.StringType
                },
                navArgument(NavRoutes.ProfileSignUp.ARG_PASSWORD) {
                    type = NavType.StringType
                },
                navArgument(NavRoutes.ProfileSignUp.ARG_TYPE_ACCOUNT) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString(NavRoutes.ProfileSignUp.ARG_EMAIL) ?: ""
            val password = backStackEntry.arguments?.getString(NavRoutes.ProfileSignUp.ARG_PASSWORD) ?: ""
            val typeAccount = backStackEntry.arguments?.getString(NavRoutes.ProfileSignUp.ARG_TYPE_ACCOUNT) ?: ""
            SignUpProfileScreen(
                navController = navController,
                paddingValues = paddingValues,
                signupViewModel = signUpViewModel,
                email = email,
                password = password,
                typeAccount = typeAccount
            )
        }

        // Interests Screen
        composable(
            route = NavRoutes.InterestsSignUp.ROUTE,
        ) {
            InterestsScreen(
                navController = navController,
                paddingValues = paddingValues,
                signupViewModel = signUpViewModel
            )
        }

        // Pantalla principal
        composable(
            route = BottomNavBar.Routes.HOME,
            arguments = emptyList()
        ) {
            HomeScreen(
                navController = navController,
                paddingValues = paddingValues,
                viewModel = internshipViewModel
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
                viewModel = internshipViewModel
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
                viewModel = internshipViewModel
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