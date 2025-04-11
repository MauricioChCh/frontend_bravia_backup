package com.example.bravia.presentation.ui.screens.business

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.R
import com.example.bravia.domain.model.Internship
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.bravia.presentation.ui.screens.student.CertificateItem
import com.example.bravia.presentation.ui.screens.student.EducationItem
import com.example.bravia.presentation.ui.screens.student.EnhanceOption
import com.example.bravia.presentation.ui.screens.student.InterestChip
import com.example.bravia.presentation.ui.screens.student.NetworkIcon
import com.example.bravia.presentation.ui.screens.student.ProfileSection
import com.example.bravia.presentation.viewmodel.BusinessViewModel

@Composable
fun BusinessProfileScreen(
    navController: NavController,
    businessViewModel: BusinessViewModel,
    paddingValues: PaddingValues
) {

    val tags = listOf("Tag 1", "Tag 2", "Tag 3", "Tag 4")
    val selectedTag by remember { mutableStateOf(tags[0]) }
    val selectedTagIndex = remember { mutableStateOf(0) }

    val description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

    LaunchedEffect(Unit) {
        businessViewModel.findAllBusinessOwnerInternship()
        businessViewModel.findAllBusinessInternshipsStarred()
    }

    val internships by businessViewModel.internshipList.collectAsState()




    MainLayout(paddingValues = paddingValues) {
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            userScrollEnabled = true
        ) {
           item {
               Box(
                   modifier = Modifier
                       .fillMaxWidth()
                       .height(180.dp)
               ) {
                   // Fondo verde (o imagen de fondo)
                   Box(
                       modifier = Modifier
                           .fillMaxWidth()
                           .height(140.dp)
                           .background(MaterialTheme.colorScheme.secondaryContainer)
                   )

                   // Foto de perfil (centrada y superpuesta)
                   Box(
                       modifier = Modifier
                           .size(130.dp)
                           .align(Alignment.BottomCenter)
                           .offset(y = 20.dp) // Ajusta para que se superponga
                           .clip(CircleShape)
                           .background(MaterialTheme.colorScheme.surface)
                           .border(3.dp, MaterialTheme.colorScheme.onTertiaryContainer, CircleShape),
                       contentAlignment = Alignment.Center
                   ) {
                       // Aquí puedes usar AsyncImage para cargar la imagen real
                       Box(
                           modifier = Modifier
                               .fillMaxSize()
                               .clip(CircleShape)
                               .background(MaterialTheme.colorScheme.tertiary)
                       )
                   }
                   // Iconos de configuración y edición
                   Row(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(horizontal = 16.dp)
                           .offset(y = 140.dp) // Ajusta para que se superponga

                       ,
                       horizontalArrangement = Arrangement.SpaceBetween
                   ) {
                       // Icono de configuración (izquierda)
                       IconButton(
                           onClick = {
                               navController.navigate(
                                   NavRoutes.Settings.ROUTE
                               )
                           }
                       ) {
                           Icon(
                               imageVector = Icons.Default.Settings,
                               contentDescription = "Configuraciones",
                               tint = MaterialTheme.colorScheme.onSurface,
                               modifier = Modifier
                                   .size(28.dp)
                           )
                       }

                       // Icono de edición (derecha)
                       IconButton(
                           onClick = { /* Función para editar */ }
                       ) {
                           Icon(
                               imageVector = Icons.Default.Edit,
                               contentDescription = "Editar",
                               tint = MaterialTheme.colorScheme.onSurface,
                               modifier = Modifier
                                   .size(28.dp)
                           )
                       }
                   }
               }
           }

            item {
                Spacer(modifier = Modifier.height(40.dp))


                // Name of the business
                Text(
                    text = "Business Name",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth().padding(start = 5.dp)
                )

                Text(
                    text = "Business Area",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth().padding(start = 5.dp)
                )

                Text(
                    text = "Heredia, Heredia, Costa Rica",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth().padding(start = 5.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Button to new internship
                Row (modifier = Modifier.fillMaxWidth()) {

                    Button(
                        onClick = { /* Acción */ },
                        modifier = Modifier
                            .width(200.dp)
                            .height(40.dp)
                            .padding(start = 5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = MaterialTheme.shapes.medium,



                        ) {
                        Text(
                            text = "New internship",
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Estos deberían ser iconos de redes sociales reales
                        NetworkIcon(iconId = R.drawable.ic_launcher_background, "Email")
                        NetworkIcon(iconId = R.drawable.ic_launcher_background, "LinkedIn")
                    }

                }


                Spacer(modifier = Modifier.height(16.dp))

                // Opciones de mejora del perfil
                EnhanceOption(text = "Enchance profile")
                EnhanceOption(text = "Enchance CV")

                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                ProfileSection(title = "About the company") {
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                ProfileSection(title = "Tags") {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(tags) { tag ->
                            InterestChip(
                                text = tag
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))


            }
        }
//        ProfileSection(title = "Our actual internships") {
//            InternshipList(
//                internships = internships,
//                navController = navController,
//                viewModel = businessViewModel,
//                page = 0,
//                textList = listOf("internships")
//            )
//        }
    }
}