package com.example.bravia


import android.os.Bundle
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import java.net.URLEncoder

class FlutterChatActivity : FlutterActivity() {
    companion object {
        const val EXTRA_INTERNSHIPS = "internships"
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        Log.d("🐛 FlutterChat", "Configurando FlutterChatActivity")

        val internshipsJson = intent.getStringExtra(EXTRA_INTERNSHIPS) ?: "[]"
        Log.d("🐛 FlutterChat", "Internships JSON: $internshipsJson")

        // Ruta específica para chat
        val route = "/chat?internships=${URLEncoder.encode(internshipsJson, "UTF-8")}"
        flutterEngine.navigationChannel.setInitialRoute(route)
        Log.d("🐛 FlutterChat", "Ruta configurada: $route")

        // Canal para funcionalidades de chat
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "com.example.bravia/chat")
            .setMethodCallHandler { call, result ->
                Log.d("🐛 FlutterChat", "Method call received: ${call.method}")
                when (call.method) {
                    "close" -> {
                        Log.d("🐛 FlutterChat", "Cerrando FlutterChatActivity")
                        finish()
                        result.success(null)
                    }
                    "startInterview" -> {
                        val internshipId = call.arguments as String
                        Log.d("🐛 FlutterChat", "Starting interview for: $internshipId")
                        result.success("interview_${System.currentTimeMillis()}")
                    }
                    "sendMessage" -> {
                        val args = call.arguments as Map<String, Any>
                        val conversationId = args["conversationId"] as String
                        val message = args["message"] as String

                        Log.d("🐛 FlutterChat", "Message: $message")

                        val response = simulateAiResponse(message)
                        result.success(response)
                    }
                    else -> result.notImplemented()
                }
            }
    }

    private fun simulateAiResponse(message: String): String {
        val responses = listOf(
            "Interesante perspectiva. ¿Podrías elaborar más?",
            "¿Cómo aplicarías eso en un proyecto real?",
            "Esa es una buena respuesta. ¿Qué desafíos anticipas?",
            "¿Tienes experiencia previa con eso?",
            "Excelente. Pasemos a la siguiente pregunta..."
        )
        return responses.random()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("🐛 FlutterChat", "FlutterChatActivity onCreate")
        super.onCreate(savedInstanceState)
    }
}