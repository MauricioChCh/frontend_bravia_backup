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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.R
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.viewmodel.StudentViewModel
import com.example.bravia.presentation.viewmodel.StudentState


/**
 * Pantalla que muestra el perfil del estudiante.
 *
 * @param navController Controlador para gestionar la navegación entre pantallas
 * @param studentViewModel ViewModel para gestionar los datos del estudiante
 * @param paddingValues Valores de relleno para aplicar a la pantalla
 */
@Composable
fun ProfileScreen(
    navController: NavController,
    studentViewModel: StudentViewModel,
    paddingValues: PaddingValues
) {
    val student by studentViewModel.student.collectAsState()
    val studentState by studentViewModel.studentState.collectAsState()

    LaunchedEffect(Unit) {
        studentViewModel.fetchStudentById()
    }

    // Variables para manejar los datos del estudiante

    var studentEducation by remember { mutableStateOf(listOf<ProfileEducation>()) }
    var studentCertificates by remember { mutableStateOf(listOf<ProfileCertificate>()) }
    var studentContacts by remember { mutableStateOf(listOf<String>()) }
    var studentName by remember { mutableStateOf("") }
    var studentInstitution by remember { mutableStateOf("No institution") }
    var studentLocation by remember { mutableStateOf("Unknown, Unknown") }
    var studentDescription by remember { mutableStateOf("No description available") }
    var studentInterests by remember { mutableStateOf(listOf<String>()) }


    // Actualizar variables cuando cambien los datos del estudiante
    student?.let { studentData ->
        studentName = "${studentData.firstName ?: ""} ${studentData.lastName ?: ""}".trim()
        studentInstitution = studentData.institution?.name ?: studentData.academicCenter ?: "No institution"
        studentLocation = "${studentData.location?.city?.name ?: "Unknown"}, ${studentData.location?.country?.name ?: "Unknown"}"
        studentDescription = studentData.description ?: "No description available"

        // Mapear interests
        studentInterests = studentData.interests?.map { it.name } ?: emptyList()

        // Mapear education - usando tanto education como degrees/colleges
        studentEducation = buildList {
            // Agregar education específica si existe
            studentData.education?.forEach { edu ->
                add(
                    ProfileEducation(
                        institution = edu.institution?.name ?: "Unknown",
                        degree = edu.degree ?: "Unknown",
                        startDate = edu.startDate ?: "Unknown"
                    )
                )
            }

            // Si no hay education específica, usar degrees y colleges
            if (studentData.education.isNullOrEmpty()) {
                studentData.degrees?.forEach { degree ->
                    val college = studentData.colleges?.firstOrNull()
                    add(
                        ProfileEducation(
                            institution = college?.name ?: "Unknown",
                            degree = degree.name ?: "Unknown",
                            startDate = "Unknown"
                        )
                    )
                }
            }
        }

        // Mapear certificates - usando tanto certificates como certifications
        studentCertificates = buildList {
            // Agregar certificates específicos si existen
            studentData.certificates?.forEach { cert ->
                add(
                    ProfileCertificate(
                        organization = cert.organization ?: "Unknown",
                        name = cert.name ?: "Unknown",
                        startDate = cert.startDate ?: "Unknown"
                    )
                )
            }

            // Si no hay certificates específicos, usar certifications
            if (studentData.certificates.isNullOrEmpty()) {
                studentData.certifications?.forEach { cert ->
                    add(
                        ProfileCertificate(
                            organization = cert.organization,
                            name = cert.name,
                            startDate = cert.date ?: "Unknown"
                        )
                    )
                }
            }
        }

        // Mapear contacts
        studentContacts = studentData.contacts?.map { it.url } ?: studentData.cvUrls ?: emptyList()
    }

        MainLayout(paddingValues = paddingValues) {
            when (studentState) {
                is StudentState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is StudentState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Error: ${(studentState as StudentState.Error).message}",
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                else -> {
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
                                        .offset(y = 20.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.surface)
                                        .border(
                                            3.dp,
                                            MaterialTheme.colorScheme.onTertiaryContainer,
                                            CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
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
                                        .offset(y = 140.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    IconButton(
                                        onClick = {
                                            navController.navigate(NavRoutes.Settings.ROUTE)
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Settings,
                                            contentDescription = "Configuraciones",
                                            tint = MaterialTheme.colorScheme.onSurface,
                                            modifier = Modifier.size(28.dp)
                                        )
                                    }

                                    IconButton(
                                        onClick = { /* Función para editar */ }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = "Editar",
                                            tint = MaterialTheme.colorScheme.onSurface,
                                            modifier = Modifier.size(28.dp)
                                        )
                                    }
                                }
                            }
                        }

                        // Información básica del perfil
                        item {
                            Spacer(modifier = Modifier.height(40.dp))

                            Text(
                                text = studentName,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Student at $studentInstitution",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )

//                            Text(
//                                text = studentLocation,
//                                style = MaterialTheme.typography.bodySmall,
//                                color = Color.Gray
//                            )

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
                            if (studentContacts.isNotEmpty()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Mostrar hasta 3 contactos
                                    studentContacts.take(3).forEach { contact ->
                                        NetworkIcon(
                                            iconId = R.drawable.ic_launcher_background,
                                            contact
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(ThemeDefaults.screenPadding))

                            // Opciones de mejora del perfil
                            EnhanceOption(text = "Enhance profile")
                            EnhanceOption(text = "Enhance CV")

                            Spacer(modifier = Modifier.height(ThemeDefaults.screenPadding))
                        }

                        item {
                            // Sección "About me"
                            if (studentDescription.isNotEmpty()) {
                                ProfileSection(title = "About me") {
                                    Text(
                                        text = studentDescription,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(ThemeDefaults.textPadding)
                                    )
                                }
                                DivisionSection()
                            }

                            // Sección "Interests"
                            if (studentInterests.isNotEmpty()) {
                                ProfileSection(title = "Interests") {
                                    LazyRow(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = ThemeDefaults.smallPadding),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        items(studentInterests) { interest ->
                                            InterestChip(interest)
                                        }
                                    }
                                }
                                DivisionSection()
                            }

                            // Sección "Education"
                            if (studentEducation.isNotEmpty()) {
                                ProfileSection(title = "Education") {
                                    Column {
                                        studentEducation.forEach { education ->
                                            EducationItem(education)
                                            Spacer(modifier = Modifier.height(8.dp))
                                        }
                                    }
                                }
                                DivisionSection()
                            }

                            // Sección "Certificates"
                            if (studentCertificates.isNotEmpty()) {
                                ProfileSection(title = "Certificates") {
                                    Column {
                                        studentCertificates.forEach { certificate ->
                                            CertificateItem(certificate)
                                            Spacer(modifier = Modifier.height(8.dp))
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(80.dp))
                        }
                    }
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
        Spacer(modifier = Modifier.height(15.dp))
    }

    @Composable
    fun ProfileSection(title: String, content: @Composable () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = ThemeDefaults.mediumPadding)
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
                modifier = Modifier.padding(
                    horizontal = ThemeDefaults.mediumPadding,
                    vertical = ThemeDefaults.smallPadding
                )
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
                // Logo de la institución
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = education.institution.take(2).uppercase(),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
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
                // Logo de la organización
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = certificate.organization.take(1).uppercase(),
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.Bold
                    )
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
