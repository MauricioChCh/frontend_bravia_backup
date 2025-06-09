package com.example.bravia.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

/* Clase lara acceder a los themes de Material3 de forma más sencilla.
 * Permite acceder a los colores, tipografías y formas de MaterialTheme
 * sin necesidad de llamar a MaterialTheme.colorScheme, etc. cada vez.
 */

// Se usa con import com.example.bravia.presentation.ui.theme.ThemeHelper as Theme
// El modificador object funciona a modo de singleton, lo que quiere decir que
// solo habra una sola intancia global, unica y accesible
object ThemeHelper {

    //Cada uno es un get de composable ya que necesitamos que se ejecute dentro de un Composable, (esto es para que el jtcompose sepa en que parte del arbol del composeable esta)
    val colors: ColorScheme
        @Composable get() = MaterialTheme.colorScheme

    val typography: Typography
        @Composable get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable get() = MaterialTheme.shapes

    // Alias directos a estilos si querés aún más brevedad
    val displayLarge: TextStyle
        @Composable get() = typography.displayLarge

    val bodyLarge: TextStyle
        @Composable get() = typography.bodyLarge
}
