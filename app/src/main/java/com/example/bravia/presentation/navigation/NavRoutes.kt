package com.example.bravia.presentation.navigation

/**
 * Contiene todas las constantes de rutas de navegación para la aplicación.
 *
 * El uso de una clase sellada con objetos garantiza la seguridad de tipos y evita errores por cadenas de ruta mal escritas.
 */
sealed class NavRoutes {
    data object SignUp : NavRoutes() {
        const val ROUTE = "signup"
    }

    data object ProfileSignUp : NavRoutes() {
        const val ROUTE = "profileSignup/{email}/{password}/{typeAccount}"
        const val ARG_EMAIL = "email"
        const val ARG_PASSWORD = "password"
        const val ARG_TYPE_ACCOUNT = "typeAccount"

        fun createRoute(email: String, password: String, typeAccount: String) = "profileSignup/$email/$password/$typeAccount"
    }

    data object InterestsSignUp : NavRoutes() {
        const val ROUTE = "interestsSignup"
    }

    data object Home : NavRoutes() {
        const val ROUTE = "home"
    }

    data object Interview : NavRoutes() {
        const val ROUTE = "interview"
    }

    data object Saved : NavRoutes() {
        const val ROUTE = "saved"
    }

    data object Profile : NavRoutes() {
        const val ROUTE = "profile"
    }



    data object InternshipDetail : NavRoutes() {
        const val ROUTE = "internshipDetail/{internshipId}"
        const val ARG_INTERNSHIP_ID = "internshipId"

        fun createRoute(internshipId: Long) = "internshipDetail/$internshipId"
    }
}