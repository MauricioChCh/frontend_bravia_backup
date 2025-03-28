package com.example.bravia.presentation.ui.screens.start

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.bravia.presentation.ui.theme.BravIATheme
import com.example.bravia.presentation.ui.theme.LightGreen
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.ui.theme.Typography
import com.example.bravia.presentation.viewmodel.LoginViewModel



@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val fakeNavController = rememberNavController() // Crea un NavController de prueba

    BravIATheme {
        LoginSavedScreen(
            navController = fakeNavController, // Usa el NavController de prueba
            paddingValues = PaddingValues(0.dp),
            loginViewModel = remember { LoginViewModel() } // Simulación del ViewModel
        )
    }
}


@Composable
fun LoginSavedScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    loginViewModel: LoginViewModel

) {
    MainLayout(paddingValues = paddingValues) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ThemeDefaults.screenPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge))
            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "BravIA",
                textAlign = TextAlign.Center,
                style = Typography.displayLarge
            )

            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge))

            Icon(
                imageVector = Icons.Default.AccountCircle, // Icono predefinido
                contentDescription = "Icono Favorito",
                modifier = Modifier.size(200.dp) // Tamaño del icono
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "User name",
                textAlign = TextAlign.Center,
                style = Typography.displaySmall
            )
            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightMedium))

            Button(
                onClick = { /* TODO Acción de ir a login */ },
                colors = ButtonDefaults.buttonColors(containerColor = LightGreen),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 90.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Continue...",
                    textAlign = TextAlign.Center,
                    style = Typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(ThemeDefaults.cardSpacerHeight))
            
            BasicText(
                text = buildAnnotatedString {
                    append("Use another") // Texto mostrado
                    addStringAnnotation(
                        tag = "URL", // Anotación para identificar el enlace
                        annotation = "https://www.example.com", // URL del hipervínculo
                        start = 0,
                        end = 12
                    )
                },
                modifier = Modifier.clickable {
                    // Lógica para manejar el clic
                    /* TODO logica de hipervinculo */
                },
                style = TextStyle(
                    textDecoration = TextDecoration.Underline, // Apariencia estilo hipervínculo
                    color = Color(0, 128, 255),
                )

            )

            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge))
            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge))
            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge))
            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightMedium))


            Button(
                onClick = { navController.navigate("signup") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(227, 229, 226)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Create an account",
                    textAlign = TextAlign.Center,
                    style = Typography.bodyMedium
                )
            }
        }
    }
}