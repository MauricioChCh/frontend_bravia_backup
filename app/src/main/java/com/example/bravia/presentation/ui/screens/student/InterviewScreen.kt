package com.example.bravia.presentation.ui.screens.student

import android.os.Bundle
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.runtime.getValue


// InterviewScreen.kt
@OptIn(ExperimentalMaterial3Api::class)
// InterviewScreen.kt
@Composable
fun InterviewScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: InterviewViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val currentUserId by remember { derivedStateOf { runBlocking { viewModel.getCurrentUserId() } } }
    val flutterEngine = remember { FlutterEngineCache.getInstance().get("flutter_engine") }

    LaunchedEffect(Unit) {
        flutterEngine?.let { engine ->
            viewModel.setupMethodChannel(engine.dartExecutor.binaryMessenger)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat con IA") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            // Sección de video/descripción
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Text(
                    "Aquí iría el video y descripción",
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }

            // Sección del chat Flutter
            flutterEngine?.let {
                AndroidView(
                    factory = { context ->
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
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
            } ?: run {
                // Mostrar un mensaje o loader si el engine no está listo
                Box(modifier = Modifier.fillMaxSize()) {
                    Text("Cargando...")
                }
            }
        }
    }
}
