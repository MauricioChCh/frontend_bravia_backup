package com.example.bravia.presentation.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.bravia.presentation.ui.theme.LightGreen
import com.example.bravia.presentation.ui.theme.Typography
import com.example.bravia.presentation.viewmodel.LoginViewModel
import com.example.studentapp.presentation.ui.theme.BravIATheme
import com.example.studentapp.presentation.ui.theme.ThemeDefaults


@Preview(showBackground = true)
@Composable
fun PreviewSignInScreen() {
    val fakeNavController = rememberNavController() // Crea un NavController de prueba

    BravIATheme {
        SignInScreen(
            navController = fakeNavController, // Usa el NavController de prueba
            paddingValues = PaddingValues(0.dp),
            loginViewModel = remember { LoginViewModel() } // Simulación del ViewModel
        )
    }
}

@Composable
fun SignInScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    loginViewModel: LoginViewModel

){
    MainLayout(paddingValues = paddingValues) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ThemeDefaults.screenPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge))
            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "BravIA",
                textAlign = TextAlign.Center,
                style = Typography.displayLarge
            )

            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge))


            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Sign in",
                textAlign = TextAlign.Left,
                style = Typography.displayMedium
            )
            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(16.dp)),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    val textoEmail = remember { mutableStateOf("") } // Estado del texto
                    val textoPassword = remember { mutableStateOf("") } // Estado del texto
                    val isPasswordVisible = remember { mutableStateOf(false) }


                    Text(text = "Email", style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

                    OutlinedTextField(
                        value = textoEmail.value, // Texto actual
                        onValueChange = { nuevoTexto -> textoEmail.value = nuevoTexto }, // Actualiza el texto
                        placeholder = { Text("example@email.com") }, // Texto de ejemplo
                        modifier = Modifier.fillMaxWidth(), // Ancho completo del campo
                        textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black) // Estilo del texto
                    )



                    Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))


                    Text(text = "Password", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

                    OutlinedTextField(
                        value = textoPassword.value, // Texto actual
                        onValueChange = { nuevoTexto -> textoPassword.value = nuevoTexto }, // Actualiza el texto
                        placeholder = { Text("password") }, // Texto de ejemplo
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
                        visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            Row {
                                Icon(
                                    imageVector = if (isPasswordVisible.value) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = "Toggle password visibility",
                                    modifier = Modifier.clickable {
                                        isPasswordVisible.value = !isPasswordVisible.value // Alterna la visibilidad de la contraseña
                                    }
                                )
                            }
                        }
                    )


                    Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge))


                    BasicText(
                        text = buildAnnotatedString {
                            append("Forgot Password?") // Texto mostrado
                            addStringAnnotation(
                                tag = "URL",
                                annotation = "https://www.example.com", // URL del hipervínculo
                                start = 0,
                                end = 15
                            )
                        },
                        modifier = Modifier
                            .clickable {
                                // TODO: lógica para abrir el enlace
                            }
                            .align(Alignment.CenterHorizontally), // Asegura que esté centrado
                        style = androidx.compose.ui.text.TextStyle(
                            textDecoration = TextDecoration.Underline, // Subrayado
                            color = Color(0, 128, 255)
                        )
                    )
                    Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))


                }
            }
            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge))


            Button(
                onClick = {  navController.navigate("home")  },
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