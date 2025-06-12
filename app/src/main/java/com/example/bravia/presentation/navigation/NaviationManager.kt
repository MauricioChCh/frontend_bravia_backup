package com.example.bravia.presentation.navigation

import com.example.bravia.domain.model.UserRole
import com.example.bravia.domain.model.UserSession

/**
 * Clase que maneja la lógica de navegación basada en roles
 */
class NavigationManager {

    /**
     * Obtiene la ruta de inicio basada en el rol del usuario
     */
    fun getStartDestination(userSession: UserSession?): String {
        return userSession?.role?.startDestination ?: NavRoutes.Start.ROUTE
    }

    /**
     * Obtiene los elementos del navbar basados en el rol
     */
    fun getBottomNavItems(userRole: UserRole): List<BottomNavBar> {
        return when (userRole) {
            UserRole.STUDENT -> BottomNavBar.items()
            UserRole.BUSINESS -> BottomNavBar.businessItems()
            UserRole.ADMIN -> BottomNavBar.adminItems() // Necesitarás implementar esto
        }
    }

    /**
     * Verifica si una ruta es accesible para un rol específico
     */
    fun isRouteAccessible(route: String, userRole: UserRole): Boolean {
        return when (userRole) {
            UserRole.STUDENT -> isStudentRoute(route)
            UserRole.BUSINESS -> isBusinessRoute(route)
            UserRole.ADMIN -> isAdminRoute(route)
        }
    }

    private fun isStudentRoute(route: String): Boolean {
        return route in listOf(
            BottomNavBar.Routes.HOME,
            BottomNavBar.Routes.SAVED,
            BottomNavBar.Routes.INTERVIEW,
            BottomNavBar.Routes.PROFILE,
            NavRoutes.InternshipDetail.ROUTE,
            NavRoutes.Settings.ROUTE
        )
    }

    private fun isBusinessRoute(route: String): Boolean {
        return route in listOf(
            BottomNavBar.Routes.BUSINESS_HOME,
            BottomNavBar.Routes.STARRED,
            BottomNavBar.Routes.BUSINESS_PROFILE,
            NavRoutes.BusinessInternshipDetail.ROUTE,
            NavRoutes.BusinessNewInternship.ROUTE,
            NavRoutes.Settings.ROUTE,
            NavRoutes.BusinessEditProfile.ROUTE
        )
    }

    private fun isAdminRoute(route: String): Boolean {
        // Implementar rutas de admin cuando las tengas
        return route in listOf(
            BottomNavBar.Routes.ADMIN_REPORTS,
            BottomNavBar.Routes.ADMIN_COMPANIES,
            BottomNavBar.Routes.ADMIN_STUDENTS,
            NavRoutes.ReportProfile.ROUTE,
            NavRoutes.StudentProfile.ROUTE,
            NavRoutes.CompanyProfile.ROUTE,
            NavRoutes.Settings.ROUTE
        )
    }

    /**
     * Redirige al usuario después del login basado en su rol
     */
    fun getPostLoginDestination(authorities: List<String>): String {
        val userRole = UserRole.fromAuthorities(authorities.map { it })
        return userRole.startDestination
    }
}