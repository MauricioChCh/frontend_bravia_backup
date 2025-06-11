package com.example.bravia.presentation.ui.screens.student

import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.flutter.embedding.android.FlutterFragment
import com.example.bravia.R
import com.example.bravia.presentation.viewmodel.InterviewViewModel
import io.flutter.embedding.engine.FlutterEngineCache
import kotlinx.coroutines.runBlocking
import com.example.bravia.domain.model.Internship
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.google.gson.Gson
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterviewScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: InterviewViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val currentUserId by remember { derivedStateOf { runBlocking { viewModel.getCurrentUserId() } } }
    val flutterEngine = remember { FlutterEngineCache.getInstance().get("flutter_engine") }

    // Lista de internships de ejemplo
    val internships = listOf(
        Internship(
            id = 1L,
            company = "TechCorp",
            city = "San Francisco",
            country = "USA",
            title = "Software Engineer Intern",
            imageUrl = "https://example.com/image1.jpg",
            publicationDate = Date(),
            duration = "3 months",
            salary = 3000.0,
            modality = "Remote",
            schedule = "Full-time",
            requirements = "Knowledge of Kotlin, Android development",
            activities = "Developing mobile applications, collaborating with the team",
            link = "https://example.com/internship1",
            isMarked = true
        ),
        Internship(
            id = 2L,
            company = "DesignStudio",
            city = "Berlin",
            country = "Germany",
            title = "UI/UX Designer Intern",
            imageUrl = "https://example.com/image2.jpg",
            publicationDate = Date(),
            duration = "6 months",
            salary = 2500.0,
            modality = "On-site",
            schedule = "Part-time",
            requirements = "Experience with Figma, Adobe XD",
            activities = "Designing user interfaces, conducting user research",
            link = "https://example.com/internship2",
            isMarked = false
        )
    )

    // Configurar la comunicación con Flutter cuando se cargue
    LaunchedEffect(flutterEngine) {
        flutterEngine?.let { engine ->
            viewModel.setupMethodChannel(engine.dartExecutor.binaryMessenger)

            // Configurar la ruta inicial de Flutter para el chat
            val internshipsJson = Gson().toJson(internships)
            println("🐛 Android: Setting Flutter route with internships: $internshipsJson")

            // IMPORTANTE: Configuar la ruta ANTES de que Flutter se renderice
            engine.navigationChannel.setInitialRoute(
                "/chat?internships=${java.net.URLEncoder.encode(internshipsJson, "UTF-8")}"
            )
        }
    }

    // MANTENER el navbar de tu app, no usar Scaffold aquí
    Column(
        modifier = Modifier
            .padding(paddingValues) // Usar los paddingValues de tu navbar
            .fillMaxSize()
    ) {
        // Opcional: Header con información del internship seleccionado
        // Esto se mostrará ARRIBA del Flutter content
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))
            Text(
                "💬 Chat con IA - Simulador de Entrevistas",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // El Flutter Fragment ocupa el resto del espacio
        flutterEngine?.let {
            AndroidView(
                factory = { context ->
                    println("🐛 Android: Creating Flutter AndroidView")
                    FrameLayout(context).apply {
                        id = R.id.flutter_container

                        val flutterFragment = FlutterFragment.withCachedEngine("flutter_engine")
                            .build<FlutterFragment>()

                        (context as FragmentActivity).supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.flutter_container, flutterFragment)
                            .commit()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Toma el espacio restante
            )
        } ?: run {
            // Mostrar loading mientras se carga Flutter
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Column(
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Cargando chat con IA...")
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))
}