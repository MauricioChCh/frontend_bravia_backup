package com.example.bravia.presentation.ui.screens.start

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.bravia.presentation.viewmodel.LoginViewModel
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.ui.theme.ThemeHelper as Theme

@Composable
fun LoginScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    loginViewModel: LoginViewModel

) {
    MainLayout(paddingValues = paddingValues) {
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(ThemeDefaults.screenPadding),
            ) {


                Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge*3))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "BravIA",
                    textAlign = TextAlign.Center,
                    style = Theme.typography.displayLarge
                )


                Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge*2))

                Button(
                    onClick = {

                        navController.navigate("signIn")

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Theme.colors.primary),
                ) {
                    Text(
                        text = "Sign in",
                        style = Theme.typography.headlineSmall,
                    )
                }

                Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

                Button(
                    onClick = {

                        navController.navigate("signup")

                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(216, 216, 216)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp)

                ) {
                    Text(
                        text = "Create account",
                        style = Theme.typography.headlineSmall
                    )
                }

                Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeightLarge*3))

                Text(
                    text = "Or continue with:",
                    style = Theme.typography.displaySmall
                )

                Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))


                Column(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(ThemeDefaults.screenPadding)

                ) {
                    Button(
                        onClick = { /* TODO Acción de ir a login */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(67, 119, 238)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp)
                    ) {
                        Row {

                            Icon(
                                imageVector = Icons.Default.Facebook, // Icono predefinido
                                contentDescription = "Icono Facebook",
                                modifier = Modifier
                                    .size(25.dp) // Tamaño del icono
                                    .align(Alignment.CenterVertically),
                            )

                            Spacer(modifier = Modifier.size(24.dp))

                            Text(
                                text = "Continue with facebook",
                                style = Theme.typography.headlineSmall,
                                color = Color.White
                            )

                        }
                    }

                    Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

                    Button(
                        onClick = { /* TODO Acción de ir a login */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(104, 104, 104)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,

                        )  {

                            Icon(
                                imageVector = Icons.Default.Web, // Icono predefinido
                                contentDescription = "Icono de google",
                                modifier = Modifier
                                    .size(25.dp) // Tamaño del icono
                                    .align(Alignment.CenterVertically),
                            )

                            Spacer(modifier = Modifier.size(24.dp))

                            Text(
                                text = "Continue with google",
                                style = Theme.typography.headlineSmall,
                                color = Color.White,

                            )

                        }
                    }
                }
            }
        }
    }
}
