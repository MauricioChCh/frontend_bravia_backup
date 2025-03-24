package com.example.studentapp.presentation.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.bravia.presentation.ui.theme.*

// Colores basados en el diseño de Figma

private val LightColorScheme = lightColorScheme(
    primary = GreenAccent,
    secondary = LightGreen,
    tertiary = Color(0xFF7B867B),
    background = BackgroundWhite,
    surface = Color.White,
    onPrimary = Color(0xFF052E08),
    onSecondary = DarkText,
    onTertiary = Color.White,
    onBackground = DarkText,
    onSurface = DarkText,
)

private val DarkColorScheme = darkColorScheme(
    primary = GreenAccent,
    secondary = LightGreen,
    tertiary = Color(0xFF7B867B),
    background = Color(0xFF1C1C1C),
    surface = Color(0xFF333333),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
)

@Composable
fun StudentAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

