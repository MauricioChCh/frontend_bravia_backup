package com.example.bravia

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bravia.navigation.NavGraph
import com.example.bravia.presentation.navigation.NavigationManager
import com.example.bravia.presentation.ui.theme.BravIATheme
import com.example.bravia.presentation.viewmodel.BusinessViewModel
import com.example.bravia.presentation.viewmodel.InternshipViewModel
import com.example.bravia.presentation.viewmodel.LoginState
import com.example.bravia.presentation.viewmodel.LoginViewModel
import com.example.bravia.presentation.viewmodel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import com.example.bravia.presentation.navigation.BottomNavBar
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.viewmodel.ThemeViewModel
import io.flutter.embedding.engine.FlutterEngineCache

//import io.flutter.embedding.engine.FlutterEngine
//import io.flutter.embedding.engine.FlutterEngineCache
//import io.flutter.embedding.engine.dart.DartExecutor

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

//    private lateinit var flutterEngine: FlutterEngine

    // Reinicia la aplicación volviendo a la actividad principal
    fun restartApp() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //Iniciamos el flutter para no tener que esperar a que se cargue al navegar a una pantalla de Flutter

//        flutterEngine = FlutterEngine(this).apply {
//            dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
//            // Espera a que el engine esté listo
//            addEngineLifecycleListener(object : FlutterEngine.EngineLifecycleListener {
//                override fun onPreEngineRestart() {}
//                override fun onEngineWillDestroy() {}
//            })
//            FlutterEngineCache.getInstance().put("flutter_engine", this)
//        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel: ThemeViewModel = hiltViewModel()
            val themeState by themeViewModel.themeState.collectAsState()
            BravIATheme(themeState = themeState){
                val navController = rememberNavController()
                val navigationManager = NavigationManager()

                // ViewModels
                val loginViewModel: LoginViewModel = hiltViewModel()
                val internshipViewModel: InternshipViewModel = hiltViewModel()
                val signUpViewModel: SignupViewModel = hiltViewModel()
                val businessViewModel: BusinessViewModel = hiltViewModel()

                // Estado de la sesión del usuario
                val userSession by loginViewModel.userSession.collectAsState()
                val loginState by loginViewModel.loginState.collectAsState()

                // Manejar la navegación después del login exitoso
                LaunchedEffect(loginState) {
                    if (loginState is LoginState.Success) {
                        val destination = (loginState as LoginState.Success).destination
                        navController.navigate(destination) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                }

                // Verificar si se debe mostrar la bottom bar
                val currentRoute = navController.currentBackStackEntry?.destination?.route
                val shouldShowBottomBar = userSession?.let { session ->
                    currentRoute != null && BottomNavBar.isBottomNavRoute(currentRoute)
                } ?: false


                Scaffold(
                    bottomBar = {
                        userSession?.let { session ->
                            if (shouldShowBottomBar) {
                                BottomNavigationBar(
                                    navController = navController,
                                    userRole = session.role
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    Column {
                        NavGraph(
                            navController = navController,
                            paddingValues = paddingValues,
                            internshipViewModel = internshipViewModel,
                            signUpViewModel = signUpViewModel,
                            loginViewModel = loginViewModel,
                            businessViewModel = businessViewModel,
                            onLogout = { restartApp() }
                        )

                    }
                    Spacer(modifier = Modifier.height(64.dp ))
                }
            }
        }

    }
//    override fun onDestroy() {
//        super.onDestroy()
//        // Limpia el FlutterEngine cuando la actividad se destruye
//        FlutterEngineCache.getInstance().remove("flutter_engine")
//    }

}


@Composable
fun BottomNavigationBar(
    navController: androidx.navigation.NavHostController,
    userRole: com.example.bravia.domain.model.UserRole
) {
    val items = BottomNavBar.getItemsForRole(userRole)

    // Esta es la clave: usar collectAsState() para hacer la ruta reactiva
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    NavigationBar(
        modifier = Modifier.height(64.dp), // ← Altura personalizada (default es ~80dp)
        tonalElevation = 0.dp // Opcional: quitar elevación
    ){
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp) // ← Iconos más pequeños
                    )
                },
                label = {
                    Text(
                        stringResource(item.title),
                        fontSize = 11.sp // ← Texto más pequeño
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

