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
import androidx.compose.material.icons.rounded.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    val company by businessViewModel.company.collectAsState()

    LaunchedEffect(Unit) {
        businessViewModel.fetchCompanyById(companyId = 2) // TODO: this has to be a variable
    }

    var businessName by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var tags by remember { mutableStateOf(listOf<String>()) }
    var businessAreas by remember { mutableStateOf(listOf<String>()) }
    var contacts by remember { mutableStateOf(listOf<String>()) }


    company?.let{
        businessName = it.name
        city = it.location.city.name
        country = it.location.country.name
        description = it.description
        tags = it.tags.map { tag -> tag.name }
        businessAreas = it.businessAreas.map { area -> area.name }
        contacts = it.contacts.map { contact -> contact.url }
    }


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

                Row( modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = businessName,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth().padding(start = 5.dp)
                    )
                    if (company?.verified == true) { // TODO: Check if is working
                        Icon(
                            imageVector = Icons.Rounded.Verified, // Replace with your desired icon
                            contentDescription = "Verified",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }


                Text(
                    text =  "$country, $city" ,
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
                        onClick = { navController.navigate(NavRoutes.BusinessNewInternship.ROUTE) },
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
            }

            item {

                DivisionSection()

                ProfileSection(title = "About the company") {
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                    )
                }

                DivisionSection()

                ProfileSection(title = "Contact") {
                    Text(
                        text = contacts.joinToString(separator = "\n") { contact -> contact },
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                    )
                }

                DivisionSection()

                ProfileSection(title = "Business Areas") {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(businessAreas) { tag ->
                            InterestChip(
                                text = tag
                            )
                        }
                    }
                }

                DivisionSection()

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

                DivisionSection()

                ProfileSection(title = "Recruiter Information") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp)
                    ) {
                        Text(
                            text = "${company?.firstName} ${company?.lastName}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                        )
                        Text(
                            text = company?.email ?: "No email provided",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            fontSize = 14.sp,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

            }
        }
    }
}


@Composable
fun DivisionSection() {
    Spacer(modifier = Modifier.height(15.dp))
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    )
}