package com.example.bravia.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bravia.R

sealed class BottomNavBar(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector
) {
    /**
     * Contiene todas las posibles rutas de navegación  del navbar como constantes.
     */
    object Routes {
        // Student routes
        const val HOME = "home"
        const val SAVED = "saved"
        const val INTERVIEW = "interview"
        const val PROFILE = "profile"

        // Business routes
        const val BUSINESS_HOME = "businessHome"
        const val STARRED = "businessStarred"
        const val BUSINESS_PROFILE = "businessProfile"

        // Admin routes
        //const val ADMIN_HOME = "adminHome"
        const val ADMIN_STUDENTS = "studentList"
        const val ADMIN_REPORTS = "reportList"
        const val ADMIN_COMPANIES = "companyList"
        const val ADMIN_SETTINGS = "adminSettings"
    }

    /**
     * Home representa el elemento de navegación para la pantalla principal.
     * Esta es la pantalla principal donde se muestra el dashboard del estudiante.
     */
    data object Home : BottomNavBar(
        Routes.HOME,
        R.string.home,
        Icons.Filled.Home
    )

    /**
     * Saved representa el elemento de navegación para la pantalla de saved.
     * Esta pantalla muestra las interships guardadas o inscritas por el estudiante
     */
    data object Saved : BottomNavBar(
        Routes.SAVED,
        R.string.saved,
        Icons.Filled.Inventory2
    )


    /**
     * Interview representa el elemento de navegación para la pantalla de Interview.
     * Esta pantalla muestra las interviews que el usuario podra hacer con la ia en base a una intership
     */
    data object Interview : BottomNavBar(
        Routes.INTERVIEW,
        R.string.interview,
        Icons.Filled.SupportAgent
    )


    /**
     * Profile representa el elemento de navegación para la pantalla de perfil.
     * Esta pantalla permite al usuario ver y editar su información de perfil.
     */
    data object Profile : BottomNavBar(
        Routes.PROFILE,
        R.string.profile,
        Icons.Filled.Person
    )

    //BUSSINESS NAVIGATION ITEMS ============================
    /**
     * BusinessHome representa el elemento de navegación para la pantalla de BusinessHome.
     * Esta pantalla permite al usuario ver y editar su información de perfil.
     */
    data object BusinessHome : BottomNavBar(
        Routes.BUSINESS_HOME,
        R.string.home,
        Icons.Filled.Inventory
    )

    data object BusinessStarred : BottomNavBar(
        Routes.STARRED,
        R.string.starred,
        Icons.Filled.Star
    )

    data object BusinessProfile : BottomNavBar(
        Routes.BUSINESS_PROFILE,
        R.string.profile,
        Icons.Filled.Person
    )

    //ADMIN NAVIGATION ITEMS ============================
    /*data object AdminHome : BottomNavBar(
        Routes.ADMIN_HOME,
        R.string.home,
        Icons.Filled.Dashboard
    )*/

    data object AdminStudents : BottomNavBar(
        Routes.ADMIN_STUDENTS,
        R.string.students,
        Icons.Filled.Person
    )

    data object AdminReports : BottomNavBar(
        Routes.ADMIN_REPORTS,
        R.string.reports,
        Icons.Filled.Report
    )

    data object AdminCompanies : BottomNavBar(
        Routes.ADMIN_COMPANIES,
        R.string.companies,
        Icons.Filled.Business
    )

    data object AdminSettings : BottomNavBar(
        Routes.ADMIN_SETTINGS,
        R.string.settings,
        Icons.Filled.Dashboard
    )


    companion object {
        /**
         * Devuelve una lista de todos los elementos de navegación inferior para mostrar en la barra de navegación.
         */
        fun items() = listOf(Home, Saved, Interview, Profile)
        /**
         * Devuelve una lista de todos los elementos de navegación inferior para mostrar en la barra de navegación.
         */
        fun businessItems() = listOf(BusinessHome, BusinessStarred, BusinessProfile)

        fun adminItems() = listOf(AdminStudents, AdminReports, AdminCompanies, AdminSettings)

        /**
         * Determina si la ruta proporcionada coincide con cualquier ruta de elemento de navegación inferior.
         *
         * @param route La ruta a verificar
         * @return True si la ruta coincide con un elemento de navegación inferior, false en caso contrario
         */
        fun isBottomNavRoute(route: String): Boolean {
            return route in listOf(
                // Student routes
                Routes.HOME, Routes.INTERVIEW, Routes.PROFILE, Routes.SAVED,
                // Business routes
                Routes.BUSINESS_HOME, Routes.STARRED, Routes.BUSINESS_PROFILE,
                // Admin routes
                Routes.ADMIN_STUDENTS, Routes.ADMIN_REPORTS, Routes.ADMIN_COMPANIES, Routes.ADMIN_SETTINGS
            )
        }
        fun getItemsForRole(role: com.example.bravia.domain.model.UserRole): List<BottomNavBar> {
            return when (role) {
                com.example.bravia.domain.model.UserRole.STUDENT -> items()
                com.example.bravia.domain.model.UserRole.BUSINESS -> businessItems()
                com.example.bravia.domain.model.UserRole.ADMIN -> adminItems()
            }
        }

    }
}