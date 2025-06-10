package com.example.bravia.presentation.ui.screens.business

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.presentation.ui.screens.shared.ErrorScreen
import com.example.bravia.presentation.ui.screens.shared.LoadingScreen
import com.example.bravia.presentation.ui.screens.student.ProfileSection
import com.example.bravia.presentation.viewmodel.BusinessState
import com.example.bravia.presentation.viewmodel.BusinessViewModel
import kotlinx.coroutines.channels.ticker
import java.text.SimpleDateFormat
import java.util.Locale


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessInternshipDetailScreen(
    navController: NavController,
    internshipId: Long,
    paddingValues: PaddingValues,
    viewModel: BusinessViewModel
) {

    val internship by viewModel.internship.collectAsState()

    when (internship)  {
        else -> LoadingScreen()
    }

    LaunchedEffect(internshipId) {
        viewModel.selectedBusinessInternshipById(internshipId) // TODO: Cambiar por una variable
    }

    var isMarked by remember { mutableStateOf(false) }

    LaunchedEffect(internship) {
        isMarked = if (internship != null) {
            internship?.isMarked == true
        } else {
            false // Reset if internship is null
        }
    }

    if (internship == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No internship found",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        }
        return
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {Text("Internship Details")},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
//                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.secondaryContainer)
            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding() * 4)
                .background(MaterialTheme.colorScheme.background)
        ) {
            internship?.let { internship ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                ) {

                    Row (
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = internship.company.first().toString(),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    ProfileSection(title = "") {
                        Row(
                            modifier = Modifier.padding(bottom = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = internship.company,
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Spacer(modifier = Modifier.weight(1f)) // Empuja el IconButton a la derecha

                            IconButton(
                                modifier = Modifier.padding(start = 16.dp),
                                onClick = {
                                    isMarked = !isMarked
                                    viewModel.bookmarkInternship (internship.id, isMarked) // TODO: Cambiar por una variable
                                },
                            ) {
                                Icon(
                                    imageVector = if (isMarked) Icons.Default.Star else Icons.Default.StarBorder,
                                    contentDescription = if (isMarked) "Remove starred" else "Add starred",
                                    tint = if (isMarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }



                    ProfileSection(title = "Internship Title") {
                        Row(
                            modifier = Modifier.padding(bottom = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = internship.title,
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    DivisionSection()

                    // TODO: eliminar este texto de prueba
                    val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales.\n" +
                            "\n" +
                            "Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit. Ut velit mauris, egestas sed, gravida nec, ornare ut, mi. Aenean ut orci vel massa suscipit pulvinar. Nulla sollicitudin. Fusce varius, ligula non tempus aliquam, nunc turpis ullamcorper nibh, in tempus sapien eros vitae ligula. Pellentesque rhoncus nunc et augue. Integer id felis. Curabitur aliquet pellentesque diam. Integer quis metus vitae elit lobortis egestas. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi vel erat non mauris convallis vehicula.\n" +
                            "\n" +
                            "Nullam at leo nec metus aliquam semper. Sed nec felis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Mauris ut leo. Cras viverra metus rhoncus sem. Nulla et lectus vestibulum urna fringilla ultrices. Phasellus eu tellus sit amet tortor gravida placerat. Integer sapien est, iaculis in, pretium quis, viverra ac, nunc. Praesent eget sem vel leo ultrices bibendum. Aenean faucibus. Morbi dolor nulla, malesuada eu, pulvinar at, mollis ac, nulla. Curabitur auctor semper nulla. Donec varius orci eget risus. Duis nibh mi, congue eu, accumsan eleifend, sagittis quis, diam.\n"

                    ProfileSection(title = "Internship Details") {

                        BoxContainer( text = "Description: $text" ) /*internship.description  TODO: cambiar por a variable*/

                        Spacer(modifier = Modifier.padding(vertical = 4.dp))

                        BoxContainer("Modality: ${internship.modality}")

                        Spacer(modifier = Modifier.padding(vertical = 4.dp))

                        BoxContainer("Activities: ${internship.activities}")

                        Spacer(modifier = Modifier.padding(vertical = 4.dp))

                        BoxContainer("Requirements: ${internship.requirements}")

                        Spacer(modifier = Modifier.padding(vertical = 4.dp))

                        BoxContainer("Schedule: ${internship.schedule}")

                        Spacer(modifier = Modifier.padding(vertical = 4.dp))

                        BoxContainer("Duration: ${internship.duration}")

                        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                        val formattedDate = dateFormat.format(internship.publicationDate)

                        Spacer(modifier = Modifier.padding(vertical = 4.dp))

                        BoxContainer("Publication Date: $formattedDate")

                        Spacer(modifier = Modifier.padding(vertical = 4.dp))

                        BoxContainer("Salary: ${internship.salary} €")

                        Spacer(modifier = Modifier.padding(vertical = 4.dp))

                        BoxContainer("Location: ${internship.city}, ${internship.country}")

                        Spacer(modifier = Modifier.padding(vertical = 4.dp))

                        BoxContainer("Link: ${internship.link}")

                    }
                }
            }
        }
    }
}

@Composable
fun BoxContainer(
    text: String = ""
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.large)
            .border(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f), shape = MaterialTheme.shapes.large)
            .padding(8.dp),
    ) {
        Text(
            text = text, // TODO: Agregar la descripcion a internship
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
