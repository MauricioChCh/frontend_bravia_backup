package com.example.bravia.presentation.ui.screens.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bravia.presentation.ui.theme.BravIATheme
import com.example.bravia.presentation.ui.theme.LightGreen
import com.example.bravia.presentation.ui.theme.Typography
import com.example.bravia.presentation.viewmodel.LoginViewModel
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun PreviewStartScreen() {
    val fakeNavController = rememberNavController() // Crea un NavController de prueba

    BravIATheme {
        StartScreen(
            navController = fakeNavController, // Usa el NavController de prueba
            paddingValues = PaddingValues(0.dp),
            loginViewModel = remember { LoginViewModel() } // Simulación del ViewModel
        )
    }
}

@Composable
fun StartScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    loginViewModel: LoginViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGreen),
        contentAlignment = Alignment.Center // Centra todo el contenido dentro del Box
    ) {
        Text(
            text = "BravIA",
            textAlign = TextAlign.Center,
            style = Typography.displayLarge
        )
    }
    LaunchedEffect(key1 = true) {
        delay(2500) // Espera 2.5 segundos
        navController.navigate("login") {
            popUpTo("start") { inclusive = true } // Elimina StartScreen del historial de navegación
        }
    }

}
