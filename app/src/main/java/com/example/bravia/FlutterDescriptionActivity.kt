package com.example.bravia

import android.os.Bundle
import android.util.Log
import com.example.bravia.domain.model.Internship
import com.google.gson.Gson
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class FlutterDescriptionActivity : FlutterActivity() {
    companion object {
        const val EXTRA_INTERNSHIP_DATA = "internship_data"
    }

    private var internship: Internship? = null

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        Log.d("🐛 FlutterDescription", "Configurando FlutterDescriptionActivity")

        val internshipJson = intent.getStringExtra(EXTRA_INTERNSHIP_DATA) ?: "{}"
        Log.d("🐛 FlutterDescription", "Internship JSON: $internshipJson")

        // Parsear el JSON a objeto Internship
        try {
            internship = Gson().fromJson(internshipJson, Internship::class.java)
            Log.d("🐛 FlutterDescription", "Internship parseada: ${internship?.title}")
        } catch (e: Exception) {
            Log.e("🐛 FlutterDescription", "Error parseando internship", e)
        }

        // Canal para comunicación
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "com.example.bravia/descriptions")
            .setMethodCallHandler { call, result ->
                Log.d("🐛 FlutterDescription", "Method call received: ${call.method}")
                when (call.method) {
                    "requestInternshipData" -> {
                        Log.d("🐛 FlutterDescription", "Enviando datos a Flutter")
                        // Enviar datos REALES a Flutter
                        val dataToSend = internship?.let { parseInternshipData(it) } ?: emptyMap()
                        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "com.example.bravia/descriptions")
                            .invokeMethod("updateInternshipData", dataToSend)
                        result.success(null)
                    }
                    "onApplyPressed" -> {
                        Log.d("🐛 FlutterDescription", "Usuario aplicó a la práctica: ${internship?.title}")
                        // Aquí puedes manejar la aplicación con el ID real
                        // Por ejemplo: aplicarAInternship(internship?.id)
                        finish() // Cerrar y volver a Android
                        result.success(null)
                    }
                    else -> result.notImplemented()
                }
            }
    }

    private fun parseInternshipData(internship: Internship): Map<String, Any> {
        return mapOf(
            "id" to internship.id,
            "title" to internship.title,
            "company" to internship.company,
            "location" to "${internship.city}, ${internship.country}",
            "description" to "Únete a ${internship.company} como ${internship.title}. Esta es una excelente oportunidad para desarrollar tus habilidades profesionales en un ambiente dinámico y colaborativo.",
            "requirements" to internship.requirements.split(",").map { it.trim() }.filter { it.isNotEmpty() },
            "activities" to internship.activities.split(",").map { it.trim() }.filter { it.isNotEmpty() },
            "benefits" to listOf(
                "Modalidad: ${internship.modality}",
                "Horario: ${internship.schedule}",
                "Duración: ${internship.duration}",
                "Salario: $${internship.salary}",
                "Ubicación: ${internship.city}, ${internship.country}"
            ),
            "salary" to internship.salary,
            "duration" to internship.duration,
            "modality" to internship.modality,
            "schedule" to internship.schedule
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("🐛 FlutterDescription", "FlutterDescriptionActivity onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("🐛 FlutterDescription", "FlutterDescriptionActivity onDestroy")
    }
}
