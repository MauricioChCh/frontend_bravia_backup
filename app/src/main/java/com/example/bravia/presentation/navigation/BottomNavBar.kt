package com.example.bravia.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bravia.R

sealed class BottomNavBar(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector
) {
    /**
     * Contiene todas las posibles rutas de navegación como constantes.
     */
    object Routes {
        const val HOME = "home"
        const val SAVED = "saved"
        const val INTERNSHIP = "internship"
        const val PROFILE = "profile"
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
     * Mentorships representa el elemento de navegación para la pantalla de interhsips.
     * Esta pantalla muestra las interships disponibles para el estudiante.
     */
    data object Internship : BottomNavBar(
        Routes.INTERNSHIP,
        R.string.internship,
        Icons.Filled.Info
    )

    data object Saved : BottomNavBar(
        Routes.SAVED,
        R.string.saved,
        Icons.Filled.AccountBox
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



    companion object {
        /**
         * Devuelve una lista de todos los elementos de navegación inferior para mostrar en la barra de navegación.
         */
        fun items() = listOf(Home, Saved, Internship, Profile)

        /**
         * Determina si la ruta proporcionada coincide con cualquier ruta de elemento de navegación inferior.
         *
         * @param route La ruta a verificar
         * @return True si la ruta coincide con un elemento de navegación inferior, false en caso contrario
         */
        fun isBottomNavRoute(route: String): Boolean {
            return route == Routes.HOME || route == Routes.INTERNSHIP || route == Routes.PROFILE || route == Routes.SAVED
        }
    }
}