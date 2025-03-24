package com.example.bravia.presentation.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bravia.navigation.BottomNavBar

/**
 * Barra de navegación inferior que permite al usuario navegar entre las principales secciones de la aplicación.
 *
 * @param navController Controlador de navegación para gestionar la navegación entre pantallas
 */
@Composable
fun BottomNavigationBar(navController: NavController) {
    // Obtener la ruta actual para resaltar el elemento seleccionado
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        // Crear un elemento de navegación para cada item definido
        BottomNavBar.items().forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = stringResource(id = item.title)) },
                label = { Text(text = stringResource(id = item.title)) },
                selected = currentRoute == item.route,
                onClick = {
                    // Navegar a la ruta seleccionada solo si no estamos ya en ella
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            // Evitar múltiples copias de la misma ruta en la pila de navegación
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}

