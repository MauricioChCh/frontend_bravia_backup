// navigation/NavGraph.kt
package com.example.bravia.navigation

import SettingsScreen
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bravia.domain.model.UserRole
import com.example.bravia.presentation.navigation.BottomNavBar
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.navigation.NavigationManager
import com.example.bravia.presentation.ui.screens.business.BusinessHomeScreen
import com.example.bravia.presentation.ui.screens.business.BusinessInternshipDetailScreen
import com.example.bravia.presentation.ui.screens.business.BusinessNewInternshipScreen
import com.example.bravia.presentation.ui.screens.business.BusinessProfileScreen
import com.example.bravia.presentation.ui.screens.business.BusinessStarredScreen
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
import com.example.bravia.presentation.viewmodel.BusinessViewModel
import com.example.bravia.presentation.viewmodel.InternshipViewModel
import com.example.bravia.presentation.viewmodel.LoginViewModel
import com.example.bravia.presentation.viewmodel.SignupViewModel

import androidx.compose.runtime.getValue
import com.example.bravia.presentation.ui.screens.admin.CompanyListScreen
import com.example.bravia.presentation.ui.screens.admin.CompanyProfileScreen
import com.example.bravia.presentation.ui.screens.admin.ReportListScreen
import com.example.bravia.presentation.ui.screens.admin.ReportProfileScreen
import com.example.bravia.presentation.ui.screens.admin.StudentListScreen
import com.example.bravia.presentation.ui.screens.admin.StudentProfileScreen
import com.example.bravia.presentation.viewmodel.AdminViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.bravia.presentation.ui.screens.business.BusinessEditProfileScreen
import com.example.bravia.presentation.ui.screens.student.InterviewScreen
import com.example.bravia.presentation.viewmodel.StudentViewModel

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
    businessViewModel: BusinessViewModel,
    studentViewModel : StudentViewModel,
    adminViewModel: AdminViewModel,
    onLogout: () -> Unit,
) {
    val navigationManager = NavigationManager()
    val userSession by loginViewModel.userSession.collectAsState()

    // Determinar la ruta de inicio basada en la sesión
    val startDestination = navigationManager.getStartDestination(userSession)

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Start.ROUTE
    ){
//    NavHost(navController = navController, startDestination = NavRoutes.BusinessHome.ROUTE) {

        //Pantallas de inicio (Siempre accesibles)=========================================

        composable(
            route = NavRoutes.Start.ROUTE
        ) {
            StartScreen(
                navController = navController,
                paddingValues = paddingValues,
            )
        }

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
            route = NavRoutes.SignIn.ROUTE
        ) {
            SignInScreen(
                navController = navController,
                paddingValues = paddingValues,
                loginViewModel = loginViewModel
            )
        }

        //Pantallas de registro========================================
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

        //Pantallas de estudiante (Student)========================================
        composable(route = BottomNavBar.Routes.HOME) {
            if (userSession?.hasRole(UserRole.STUDENT) == true) {
                HomeScreen(
                    navController = navController,
                    paddingValues = paddingValues,
                    viewModel = internshipViewModel
                )
            } else {
                // Redirigir o mostrar error de acceso
                UnauthorizedScreen(navController)

            }
        }
        // Pantalla de interviews
        composable(
            route = BottomNavBar.Routes.INTERVIEW,
            arguments = emptyList()
        ) {
            if (userSession?.hasRole(UserRole.STUDENT) == true) {
                InterviewScreen(
                    navController = navController,
                    paddingValues = paddingValues,
                )
            }
            else {
                // Redirigir o mostrar error de acceso
                UnauthorizedScreen(navController)

            }

        }

        // Pantalla de guardados
        composable(route = BottomNavBar.Routes.SAVED) {
            if (userSession?.hasRole(UserRole.STUDENT) == true) {
                SavedInternshipsScreen(
                    navController = navController,
                    paddingValues = paddingValues,
                    viewModel = internshipViewModel
                )
            } else {
                // Redirigir o mostrar error de acceso
                UnauthorizedScreen(navController)

            }
        }
        // Pantalla de perfil
        composable(route = BottomNavBar.Routes.PROFILE) {
            if (userSession?.hasRole(UserRole.STUDENT) == true) {
                ProfileScreen(
                    navController = navController,
                    paddingValues = paddingValues,
                    studentViewModel = studentViewModel
                )
            } else {
                // Redirigir o mostrar error de acceso
                UnauthorizedScreen(navController)

            }
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
            if (userSession?.hasRole(UserRole.STUDENT) == true) {
                val internshipId = backStackEntry.arguments?.getLong(NavRoutes.InternshipDetail.ARG_INTERNSHIP_ID) ?: -1L
                InternshipDetailScreen(
                    navController = navController,
                    internshipId = internshipId.toString(),
                    paddingValues = PaddingValues(0.dp),
                    viewModel = internshipViewModel
                )
            } else {
                // Redirigir o mostrar error de acceso
                UnauthorizedScreen(navController)

            }
        }










        // Business screens=====================================
        composable(route = NavRoutes.BusinessHome.ROUTE) {
            if (userSession?.hasRole(UserRole.BUSINESS) == true) {
                BusinessHomeScreen(
                    navController = navController,
                    businessViewModel = businessViewModel
                )
            } else {
                UnauthorizedScreen(navController)

            }
        }

        composable(
            route = NavRoutes.BusinessInternshipDetail.ROUTE,
            arguments = listOf(
                navArgument(NavRoutes.BusinessInternshipDetail.ARG_INTERNSHIP_ID) {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            if (userSession?.hasRole(UserRole.BUSINESS) == true) {
                val internshipId = backStackEntry.arguments?.getLong(NavRoutes.BusinessInternshipDetail.ARG_INTERNSHIP_ID) ?: -1L
                BusinessInternshipDetailScreen(
                    navController = navController,
                    internshipId = internshipId,
                    paddingValues = PaddingValues(0.dp),
                    viewModel = businessViewModel
                )
            } else {
                // Redirigir o mostrar error de acceso
                UnauthorizedScreen(navController)

            }
        }

        composable(route = NavRoutes.BusinessStarred.ROUTE) {
            if (userSession?.hasRole(UserRole.BUSINESS) == true) {
                BusinessStarredScreen(
                    navController = navController,
                    businessViewModel = businessViewModel
                )
            } else {
                UnauthorizedScreen(navController)
            }
        }

        composable(route = NavRoutes.BusinessProfile.ROUTE) {
            if (userSession?.hasRole(UserRole.BUSINESS) == true) {
                BusinessProfileScreen(
                    navController = navController,
                    businessViewModel = businessViewModel,
                    paddingValues = PaddingValues(0.dp)
                )
            } else {
                UnauthorizedScreen(navController)
            }
        }

        composable(route = NavRoutes.BusinessNewInternship.ROUTE) {
            if (userSession?.hasRole(UserRole.BUSINESS) == true) {
                BusinessNewInternshipScreen(
                    navController = navController,
                    businessViewModel = businessViewModel
                )
            } else {
                UnauthorizedScreen(navController)
            }
        }

        composable(route = NavRoutes.BusinessEditProfile.ROUTE) {
            if (userSession?.hasRole(UserRole.BUSINESS) == true) {
                BusinessEditProfileScreen(
                    businessViewModel = businessViewModel,
                    navController = navController,
                    paddingValues = PaddingValues(0.dp)
                )
            } else {
                UnauthorizedScreen(navController)
            }
        }

        //Admin Screens

        composable(route = NavRoutes.CompanyList.ROUTE) {
            CompanyListScreen(
                navController = navController,
                viewModel = adminViewModel // o el que estés usando para Company
            )
        }

        composable(route = NavRoutes.StudentList.ROUTE) {
            StudentListScreen(
                navController = navController,
                viewModel = adminViewModel // o el que estés usando para student
            )
        }

        composable(route = NavRoutes.ReportList.ROUTE) {
            ReportListScreen(
                navController = navController,
                viewModel = adminViewModel // o el que estés usando para student
            )
        }

        composable(
            route = NavRoutes.ReportProfile.ROUTE,
            arguments = listOf(
                navArgument(NavRoutes.ReportProfile.ARG_REPORT_ID) {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val reportId = backStackEntry.arguments?.getLong(NavRoutes.ReportProfile.ARG_REPORT_ID) ?: -1L
            ReportProfileScreen(
                navController = navController,
                reportId = reportId,
                paddingValues = PaddingValues(0.dp),
                viewModel = adminViewModel,
                onUserClick = { userName ->
                    // Puedes navegar a la pantalla del perfil del usuario o mostrar un diálogo, etc.
                    Log.d("Nav", "Usuario clickeado: $userName")
                    // navController.navigate("userProfile/$userName") // ejemplo opcional
                }
            )
        }

        composable(
            route = NavRoutes.StudentProfile.ROUTE,
            arguments = listOf(
                navArgument(NavRoutes.StudentProfile.ARG_STUDENT_ID) {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getLong(NavRoutes.StudentProfile.ARG_STUDENT_ID) ?: -1L
            StudentProfileScreen(
                navController = navController,
                userId = studentId,
                paddingValues = PaddingValues(0.dp),
                viewModel = adminViewModel,
                onUserClick = { userName ->
                    Log.d("Nav", "Usuario clickeado: $userName")
                    // navController.navigate("userProfile/$userName")
                }
            )
        }

        composable(
            route = NavRoutes.CompanyProfile.ROUTE,
            arguments = listOf(
                navArgument(NavRoutes.CompanyProfile.ARG_COMPANY_ID) {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val companyId = backStackEntry.arguments?.getLong(NavRoutes.CompanyProfile.ARG_COMPANY_ID) ?: -1L
            CompanyProfileScreen(
                navController = navController,
                userId = companyId,
                paddingValues = PaddingValues(0.dp),
                viewModel = adminViewModel,
                onUserClick = { userName ->
                    Log.d("Nav", "Usuario clickeado: $userName")
                    // navController.navigate("userProfile/$userName")
                }
            )
        }

        //Main SCREENS===========================================


        //Shared
        //Student settings
        composable(
            route = NavRoutes.Settings.ROUTE,
        ) {
            SettingsScreen(
                onNavigateToLogin = onLogout, // ← Pasa el callback a SettingsScreen
                loginViewModel = loginViewModel,
                navController = navController,
            )
        }

        composable(route = BottomNavBar.Routes.ADMIN_SETTINGS) {
            if (userSession?.hasRole(UserRole.ADMIN) == true) {
                SettingsScreen(
                    onNavigateToLogin = onLogout,
                    loginViewModel = loginViewModel,
                    navController = navController
                )
            } else {
                UnauthorizedScreen(navController)
            }
        }


    }
}

@Composable
fun UnauthorizedScreen(navController: NavHostController) {
    // Implementar pantalla de acceso no autorizado
    // Por ahora, redirigir al login
    navController.navigate(NavRoutes.Login.ROUTE) {
        popUpTo(0) { inclusive = true }
    }
}
