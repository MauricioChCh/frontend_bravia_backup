package com.example.bravia.navigation

/**
 * Contiene todas las constantes de rutas de navegación para la aplicación.
 *
 * El uso de una clase sellada con objetos garantiza la seguridad de tipos y evita errores por cadenas de ruta mal escritas.
 */
sealed class NavRoutes {
    data object Home : NavRoutes() {
        const val ROUTE = "home"
    }

    data object Interview : NavRoutes() {
        const val ROUTE = "interview"
    }

    data object Profile : NavRoutes() {
        const val ROUTE = "profile"
    }

    data object InternshipDetail : NavRoutes() {
        const val ROUTE = "internshipDetail/{internshipId}"
        const val ARG_MENTOR_ID = "internshipId"

        fun createRoute(internshipId: Long) = "mentorDetail/$internshipId"
    }
}