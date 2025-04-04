// navigation/NavGraph.kt
package com.example.bravia.navigation

import SettingsScreen
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
import com.example.bravia.presentation.ui.screens.student.HomeScreen
import com.example.bravia.presentation.ui.screens.start.InterestsScreen
import com.example.bravia.presentation.ui.screens.student.InternshipDetailScreen
import com.example.bravia.presentation.ui.screens.start.LoginSavedScreen
import com.example.bravia.presentation.ui.screens.start.LoginScreen
import com.example.bravia.presentation.ui.screens.student.ProfileScreen
import com.example.bravia.presentation.ui.screens.student.SavedInternshipsScreen
import com.example.bravia.presentation.ui.screens.start.SignInScreen
import com.example.bravia.presentation.ui.screens.start.SignUpProfileScreen
import com.example.bravia.presentation.ui.screens.start.SignUpScreen
import com.example.bravia.presentation.ui.screens.start.StartScreen
import com.example.bravia.presentation.viewmodel.InternshipViewModel
import com.example.bravia.presentation.viewmodel.LoginViewModel
import com.example.bravia.presentation.viewmodel.SignupViewModel


/**
 * NavGraph is a composable function that defines the navigation graph for the application.
 * It sets up the navigation host and specifies the different screens and their corresponding routes.
 *
 * @param navController The NavHostController used for navigation.
 * @param paddingValues The padding values to be applied to the screens.
 * @param internshipViewModel The ViewModel for managing internship-related data.
 * @param signUpViewModel The ViewModel for managing sign-up-related data.
 * @param loginViewModel The ViewModel for managing login-related data.
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    internshipViewModel: InternshipViewModel,
    signUpViewModel: SignupViewModel,
    loginViewModel: LoginViewModel,
) {
    NavHost(navController = navController, startDestination = NavRoutes.Start.ROUTE) {

        //start screens=========================================
        composable(
            route = NavRoutes.Login.ROUTE
        ) {
            LoginScreen(
                navController = navController,
                paddingValues = paddingValues,
                loginViewModel = loginViewModel
            )
        }

        composable(
            route = NavRoutes.LoginSaved.ROUTE
        ) {
            LoginSavedScreen(
                navController = navController,
                paddingValues = paddingValues,
                loginViewModel = loginViewModel
            )
        }

        composable(
            route = NavRoutes.Start.ROUTE
        ) {
            StartScreen(
                navController = navController,
                paddingValues = paddingValues,
                loginViewModel = loginViewModel
            )
        }

        composable(
            route = NavRoutes.SignIn.ROUTE
        ) {
            SignInScreen(
                navController = navController,
                paddingValues = paddingValues,
                loginViewModel = loginViewModel
            )
        }

        //SING-UP screens========================================
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
        ) {
            SignUpProfileScreen(
                navController = navController,
                signupViewModel = signUpViewModel
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



        //Main SCREENS===========================================
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

        // Pantalla de interviews
        composable(
            route = BottomNavBar.Routes.INTERVIEW,
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

        //Shared
        //Student settings
        composable(
            route = NavRoutes.Settings.ROUTE,
        ) {
            SettingsScreen()
        }

    }
}