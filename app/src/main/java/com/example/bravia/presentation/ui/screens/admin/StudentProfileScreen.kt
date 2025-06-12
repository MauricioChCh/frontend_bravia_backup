package com.example.bravia.presentation.ui.screens.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.bravia.presentation.ui.screens.student.InterestChip
import com.example.bravia.presentation.viewmodel.AdminViewModel

@Composable
fun StudentProfileScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: AdminViewModel,
    userId: Long,
    onUserClick: (String) -> Unit,
) {
    val student by viewModel.student.collectAsState()
    val adminState by viewModel.adminState.collectAsState()

    LaunchedEffect(userId) {
        viewModel.fetchStudentById(userId)
    }

    MainLayout(paddingValues = paddingValues) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                    )
                    Box(
                        modifier = Modifier
                            .size(130.dp)
                            .align(Alignment.BottomCenter)
                            .offset(y = 20.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface)
                            .border(3.dp, MaterialTheme.colorScheme.onTertiaryContainer, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        // Puedes cargar imagen aquí con AsyncImage si tienes URL
                    }
                    /*Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .offset(y = 140.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = { navController.navigate("settings") }) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Configuraciones",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        IconButton(onClick = { *//* Editar perfil *//* }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }*/
                }
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = student?.userInput?.firstName ?: "Nombre del usuario",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = student?.academicCenter ?: "Universidad o institución",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            viewModel.banUserById(userId, true)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text(text = "HABILITAR", color = Color.Black)
                    }

                    Button(
                        onClick = {
                            viewModel.banUserById(userId, false)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .padding(start = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text(text = "DESHABILITAR", color = Color.White)
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    // Iconos de redes sociales
                }
                Spacer(modifier = Modifier.height(16.dp))
/*                EnhanceOption("Enhance profile")
                EnhanceOption("Enhance CV")*/
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                ProfileSection(title = "About me") {
                    Text(
                        text = student?.description ?: "Descripción del usuario",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                /*Spacer(modifier = Modifier.height(16.dp))
                ProfileSection(title = "Education") {
                    // EducationItem(...)
                }
                Spacer(modifier = Modifier.height(16.dp))
                ProfileSection(title = "Certificates") {
                    // CertificateItem(...)
                }
                Spacer(modifier = Modifier.height(80.dp))*/
            }
        }

        // Footer barra
        /*Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.Gray)
                Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.Gray)
                Icon(Icons.Default.Person, contentDescription = "Person", tint = Color.Green)
            }
        }*/
    }
}

/*@Composable
fun EnhanceOption(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
        Icon(
            imageVector = Icons.Outlined.Star,
            contentDescription = "Enhance",
            tint = Color.Black,
            modifier = Modifier.size(16.dp)
        )
    }
}*/
