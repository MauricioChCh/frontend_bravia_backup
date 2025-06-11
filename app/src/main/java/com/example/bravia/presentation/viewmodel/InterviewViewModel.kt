package com.example.bravia.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.data.local.AuthPreferences
import com.example.bravia.domain.model.Internship
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class InterviewViewModel @Inject constructor(
    private val authPreferences: AuthPreferences
) : ViewModel() {
    //Estado del flutter engine
    private val _flutterState = MutableStateFlow<FlutterState>(FlutterState.Loading)
    val flutterState: StateFlow<FlutterState> = _flutterState.asStateFlow()


    // Estado del chat
    private val _chatState = MutableStateFlow<ChatState>(ChatState.Idle)
    val chatState: StateFlow<ChatState> = _chatState.asStateFlow()

    // Obtener el ID del usuario actual desde AuthPreferences
    suspend fun getCurrentUserId(): String? {
        return authPreferences.getUsername()
    }




    // Inicializamos  el FlutterEngine
    fun initializeFlutterEngine(activity: FragmentActivity) {
        _flutterState.value = FlutterState.Loading
        viewModelScope.launch {
            try {
                val engine = FlutterEngine(activity).apply {
                    dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())

                    // Configuración inicial
                    val internshipsJson = getInternshipsJson()
                    navigationChannel.setInitialRoute("/chat?internships=${URLEncoder.encode(internshipsJson, "UTF-8")}")

                    FlutterEngineCache.getInstance().put("flutter_engine", this)
                }
                _flutterState.value = FlutterState.Ready(engine)
            } catch (e: Exception) {
                _flutterState.value = FlutterState.Error(e.message ?: "Error al inicializar Flutter")
            }
        }
    }

    private fun getInternshipsJson(): String {
        //Esto debe ser de repository
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
        return Gson().toJson(internships)
    }


    sealed class FlutterState {
        object Loading : FlutterState()
        data class Ready(val engine: FlutterEngine) : FlutterState()
        data class Error(val message: String) : FlutterState()
    }


    //CHat ================================================================

    // Procesar mensajes del chat
    fun processMessage(message: String) {
        _chatState.value = ChatState.Processing
        viewModelScope.launch {
            try {
                // Aquí iría la lógica para procesar el mensaje con IA
                // Por ahora simulamos una respuesta después de 1 segundo
                delay(1000)
                _chatState.value = ChatState.Response("Respuesta simulada para: $message")
            } catch (e: Exception) {
                _chatState.value = ChatState.Error("Error al procesar el mensaje")
            }
        }
    }


    sealed class ChatState {
        object Idle : ChatState()
        object Processing : ChatState()
        data class Response(val message: String) : ChatState()
        data class Error(val message: String) : ChatState()
    }

    private lateinit var methodChannel: MethodChannel

    fun setupMethodChannel(messenger: BinaryMessenger) {
        methodChannel = MethodChannel(messenger, "com.example.bravia/chat").apply {
            setMethodCallHandler { call, result ->
                when (call.method) {
                    "closeChat" -> {
                        // Manejar cierre del chat
                        result.success(null)
                    }
                    else -> result.notImplemented()
                }
            }
        }
    }


}