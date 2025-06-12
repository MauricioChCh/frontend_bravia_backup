package com.example.bravia.presentation.ui.screens.student

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.R
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.bravia.presentation.ui.theme.ThemeDefaults


/**
 * Pantalla que muestra el perfil del estudiante.
 *
 * @param navController Controlador para gestionar la navegación entre pantallas
 * @param paddingValues Valores de relleno para aplicar a la pantalla
 */
@Composable
fun ProfileScreen(
    navController: NavController,
    paddingValues: PaddingValues
) {

    //Datos quemados momentaneos/ sustituir por reales
    // Lista de intereses de ejemplo
    val interests = listOf("Frontend", "Backend", "CyberSecurity", "Figma", "Docker", "Kotlin")

    // certificate y education seran listas
    val education = ProfileEducation(
        institution = "Universidad Nacional",
        degree = "Ingeniería Informática",
        startDate = "12/02/2021"
    )

    val certificate = ProfileCertificate(
        organization = "Cisco",
        name = "CCNA 2 Cisco networking",
        startDate = "12/02/2021"
    )


    MainLayout(paddingValues = paddingValues) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                // Header con imagen de fondo
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
//                // Espacio para compensar el desplazamiento de la foto de perfil
//                Spacer(modifier = Modifier.height(40.dp))
            }

            // Información básica del perfil
            item {
                Spacer(modifier = Modifier.height(40.dp)) // Espacio para acomodar el perfil

                //Traer nombre
                Text(
                    text = "Proga Mador",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                //Traer lgar de estudio
                Text(
                    text = "Student at Universidad Nacional",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                //Trraer ubicacion
                Text(
                    text = "Heredia, Heredia, Costa Rica",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(ThemeDefaults.screenPadding))


                // Botón CV
                Button(
                    onClick = { /* Acción CV */ },
                    modifier = Modifier
                        .width(200.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "CV",
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }

                Spacer(modifier = Modifier.height(ThemeDefaults.screenPadding))


                // Redes sociales
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Estos deberían ser iconos de redes sociales reales
                    NetworkIcon(iconId = R.drawable.ic_launcher_background, "Email")
                    NetworkIcon(iconId = R.drawable.ic_launcher_background, "GitHub")
                    NetworkIcon(iconId = R.drawable.ic_launcher_background, "LinkedIn")
                }

                Spacer(modifier = Modifier.height(ThemeDefaults.screenPadding))


                // Opciones de mejora del perfil
                EnhanceOption(text = "Enchance profile")
                EnhanceOption(text = "Enchance CV")

                Spacer(modifier = Modifier.height(ThemeDefaults.screenPadding))

            }

            item {
                // Sección "About me"
                ProfileSection(title = "About me") {
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed elementum tortor vitae bibendum pretium. Integer lectus leo, laoreet eu ultricies in, egestas ac diam. Proin rutrum lorem lorem. Praesent in eros iaculis, consequat odio eu, tincidunt",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(ThemeDefaults.textPadding)
                    )
                }

                Spacer(modifier = Modifier.height(ThemeDefaults.screenPadding))


                // Sección "Interests"
                ProfileSection(title = "Interests") {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = ThemeDefaults.smallPadding),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(interests) { interest ->
                            InterestChip(interest)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(ThemeDefaults.screenPadding))

                // Sección "Education"
                ProfileSection(title = "Education") {
                    EducationItem(education)
                }

                Spacer(modifier = Modifier.height(ThemeDefaults.screenPadding))


                // Sección "Certificates"
                ProfileSection(title = "Certificates") {
                    CertificateItem(certificate)
                }

                Spacer(modifier = Modifier.height(80.dp)) // Espacio para la navegación inferior
            }
        }

//            // Barra de navegación inferior
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(100.dp)
//                    .background(Color.White)
//
//            ) {
//                Row(
//                    modifier = Modifier.fillMaxSize(),
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.Gray)
//                    Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.Gray)
//                    Icon(Icons.Default.Person, contentDescription = "Person", tint = Color.Green)
//                }
//            }

        }
    }

@Composable
fun ProfileSection(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal =ThemeDefaults.mediumPadding)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = ThemeDefaults.smallPadding)
        )

        content()
    }
}

@Composable
fun NetworkIcon(iconId: Int, contentDescription: String) {
    IconButton(
        onClick = { /* Acción de red social */ },
        modifier = Modifier.size(40.dp)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            tint = Color.Black
        )
    }
}

@Composable
fun EnhanceOption(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Icon(
            imageVector = Icons.Outlined.Star,
            contentDescription = "Enhance",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun InterestChip(text: String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier.height(36.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = ThemeDefaults.mediumPadding, vertical = ThemeDefaults.smallPadding)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

data class ProfileEducation(
    val institution: String,
    val degree: String,
    val startDate: String
)

@Composable
fun EducationItem(education: ProfileEducation) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ThemeDefaults.smallPadding),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ThemeDefaults.smallPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo de la institución (simulado con un Box)
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                // Aquí iría un Image con el logo real
                Text("UN", color = Color.White)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = education.institution,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = education.degree
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Calendario",
                        modifier = Modifier.size(16.dp),
                        tint = Color.Black
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "Start on ${education.startDate}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

data class ProfileCertificate(
    val organization: String,
    val name: String,
    val startDate: String
)

@Composable
fun CertificateItem(certificate: ProfileCertificate) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ThemeDefaults.smallPadding),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo de la organización (simulado con un Box)
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.Blue),
                contentAlignment = Alignment.Center
            ) {
                // Aquí iría un Image con el logo real
                Text("C", color = Color.White)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = certificate.organization,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = certificate.name
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Calendario",
                        modifier = Modifier.size(16.dp),
                        tint = Color.Black
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "Start on ${certificate.startDate}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}