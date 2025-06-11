package com.example.bravia.presentation.ui.screens.student

import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

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

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import io.flutter.embedding.engine.FlutterEngineCache


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterviewScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: InterviewViewModel = hiltViewModel(),

) {
    val context = LocalContext.current
    val activity = context as FragmentActivity
    val flutterState by viewModel.flutterState.collectAsState()

    // Inicializar Flutter al entrar a la pantalla
    LaunchedEffect(Unit) {
        viewModel.initializeFlutterEngine(activity)
    }

//    LaunchedEffect(Unit) {
//        if (viewModel.flutterState.value is InterviewViewModel.FlutterState.Loading) {
//            viewModel.initializeFlutterEngine(activity)
//        }
//    }

    // Limpiar al salir
    DisposableEffect(Unit) {
        onDispose {
            FlutterEngineCache.getInstance().remove("flutter_engine")
        }
    }

    // MANTENER el navbar de tu app, no usar Scaffold aquí
    Column(
        modifier = Modifier
            .padding(paddingValues) // Usar los paddingValues de tu navbar
            .fillMaxSize()
    ) {
        // Header opcional
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp)
        ) {
            Text(
                "💬 Chat con IA - Simulador de Entrevistas",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Contenido Flutter
        when (val state = flutterState) {
            is InterviewViewModel.FlutterState.Loading -> LoadingView()
            is InterviewViewModel.FlutterState.Ready -> FlutterContentView(paddingValues)
            is InterviewViewModel.FlutterState.Error -> ErrorView {
                viewModel.initializeFlutterEngine(activity)
            }
        }
    }
}
@Composable
private fun FlutterContentView(
    paddingValues: PaddingValues
) {
    AndroidView(
        factory = { context ->
            FrameLayout(context).apply {
                id = R.id.flutter_container

                FlutterFragment.withCachedEngine("flutter_engine")
                    .build<FlutterFragment>()
                    .also { fragment ->
                        (context as FragmentActivity).supportFragmentManager
                            .beginTransaction()
                            .replace(id, fragment)
                            .commit()
                    }
            }
        },
        modifier = Modifier
            .fillMaxWidth()


    )
}

@Composable
private fun LoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text("Cargando chat con IA...")
        }
    }
}

@Composable
private fun ErrorView(onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Error al cargar el chat", color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetry) {
                Text("Reintentar")
            }
        }
    }
}