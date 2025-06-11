package com.example.bravia


import android.os.Bundle
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class FlutterDemoActivity : FlutterActivity() {

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        Log.d("🐛 FlutterDemo", "Configurando FlutterDemoActivity")

        // Ruta específica para la demo ANTES DEL RENDERIZADO
        flutterEngine.navigationChannel.setInitialRoute("/demo")
        Log.d("🐛 FlutterDemo", "Ruta configurada: /demo")

        // Canal básico para cerrar, esto sirve para invocar metodos nativos
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "com.example.bravia/demo")
            .setMethodCallHandler { call, result ->
                Log.d("🐛 FlutterDemo", "Method call received: ${call.method}")
                when (call.method) {
                    "close" -> {
                        Log.d("🐛 FlutterDemo", "Cerrando FlutterDemoActivity")
                        finish()
                        result.success(null)
                    }
                    else -> result.notImplemented()
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("🐛 FlutterDemo", "FlutterDemoActivity onCreate")
        super.onCreate(savedInstanceState)
    }
    override fun onDestroy() {
        Log.d("🐛 FlutterDemo", "FlutterDemoActivity onDestroy")
        super.onDestroy()
    }
}

