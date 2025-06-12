package com.example.bravia.presentation.ui.screens.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.bravia.presentation.viewmodel.AdminViewModel

@Composable
fun CompanyProfileScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: AdminViewModel,
    userId: Long,
    onUserClick: (String) -> Unit,
) {
    val company by viewModel.company.collectAsState()
    val adminState by viewModel.adminState.collectAsState()

    LaunchedEffect(userId) {
        viewModel.fetchCompanyByCompanyId(userId)  // Asegúrate que tu ViewModel tiene este método
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
                        // Aquí podrías cargar la imagen de company.imageUrl si tienes alguna librería para imágenes
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
                    text = (listOfNotNull(company?.firstName, company?.lastName).joinToString(" ").takeIf { it.isNotBlank() }
                        ?: "Nombre del usuario"),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = company?.name ?: "Nombre de la empresa",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* Ver CV o alguna acción */ },
                    modifier = Modifier
                        .width(200.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(text = "BANEAR", color = Color.Black)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    // Iconos de redes sociales o contactos
                }
                Spacer(modifier = Modifier.height(16.dp))
/*                EnhanceOption("Enhance profile")
                EnhanceOption("Enhance CV")*/
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                ProfileSection(title = "About") {
                    Text(
                        text = company?.description ?: "Descripción de la empresa",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                /* Puedes agregar más secciones específicas para Company aquí */

            }
        }
    }
}