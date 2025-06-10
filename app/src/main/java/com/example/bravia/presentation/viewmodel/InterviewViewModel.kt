package com.example.bravia.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.data.local.AuthPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterviewViewModel @Inject constructor(
    private val authPreferences: AuthPreferences
) : ViewModel() {
    // Estado del chat
    private val _chatState = mutableStateOf<ChatState>(ChatState.Idle)
    val chatState: ChatState get() = _chatState.value

    // Obtener el ID del usuario actual desde AuthPreferences
    suspend fun getCurrentUserId(): String? {
        return authPreferences.getUsername()
    }

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