package com.example.bravia.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bravia.presentation.navigation.BottomNavBar

/**
 * Barra de navegación inferior que permite al usuario navegar entre las principales secciones de la aplicación.
 *
 * @param navController Controlador de navegación para gestionar la navegación entre pantallas
 */
@Composable
fun BottomNavigationBar(navController: NavController, route: String? = null) {
    // Obtener la ruta actual para resaltar el elemento seleccionado
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box{
        Column{
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 0.2.dp,
                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f)
            )
            NavigationBar(
                modifier = Modifier.height(56.dp).fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
                // Crear un elemento de navegación para cada item definido
                if (route == "businessHome") {
                    BottomNavBar.businessItems().forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = stringResource(id = item.title)) },
                            //label = { Text(text = stringResource(id = item.title)) },
                            selected = currentRoute == item.route,
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.surface,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = MaterialTheme.colorScheme.primary
                            ),
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
                } else {
                    BottomNavBar.items().forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = stringResource(id = item.title)) },
                            //label = { Text(text = stringResource(id = item.title)) },
                            selected = currentRoute == item.route,
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.surface,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = MaterialTheme.colorScheme.primary
                            ),
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
        }
    }
}

