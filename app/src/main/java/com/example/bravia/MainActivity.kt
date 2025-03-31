package com.example.bravia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bravia.data.datasource.businessArea.BusinessAreaDataSourceImpl
import com.example.bravia.data.datasource.college.CollegeDataSourceImpl
import com.example.bravia.data.datasource.degree.DegreeDataSourceImpl
import com.example.bravia.data.datasource.interest.InterestDataSourceImpl
import com.example.bravia.data.datasource.intership.InternshipDataSourceImpl
import com.example.bravia.data.mapper.BusinessAreaMapper
import com.example.bravia.data.mapper.CollegeMapper
import com.example.bravia.data.mapper.DegreeMapper
import com.example.bravia.data.mapper.InterestMapper
import com.example.bravia.data.mapper.InternshipMapper
import com.example.bravia.data.repository.BusinessAreaRepositoryImpl
import com.example.bravia.data.repository.CollegeRepositoryImpl
import com.example.bravia.data.repository.DegreeRepositoryImpl
import com.example.bravia.data.repository.InterestRepositoryImpl
import com.example.bravia.data.repository.InternshipRepositoryImpl
import com.example.bravia.navigation.NavGraph
import com.example.bravia.presentation.factory.InternshipViewModelFactory
import com.example.bravia.presentation.factory.SignupViewModelFactory
import com.example.bravia.presentation.navigation.NavRoutes
import com.example.bravia.presentation.ui.components.BottomNavigationBar
import com.example.bravia.presentation.ui.theme.BravIATheme
import com.example.bravia.presentation.viewmodel.InternshipViewModel
import com.example.bravia.presentation.viewmodel.LoginViewModel
import com.example.bravia.presentation.viewmodel.SignupViewModel

class MainActivity : ComponentActivity() {

    // Inicialización de mappers
    private val interestMapper = InterestMapper()
    private val collegeMapper = CollegeMapper()
    private val degreeMapper = DegreeMapper()
    private val businessAreaMapper = BusinessAreaMapper()

    // Inicialización de las fuentes de datos
    private val interestDataSource = InterestDataSourceImpl()
    private val collegeDataSource = CollegeDataSourceImpl()
    private val degreeDataSource = DegreeDataSourceImpl()
    private val businessAreaDataSource = BusinessAreaDataSourceImpl()

    // Inicialización de los repositorios
    private val interestRepository = InterestRepositoryImpl(interestDataSource, interestMapper)
    private val collegeRepository = CollegeRepositoryImpl(collegeDataSource, collegeMapper)
    private val degreeRepository = DegreeRepositoryImpl(degreeDataSource, degreeMapper)
    private val businessAreaRepository = BusinessAreaRepositoryImpl(businessAreaDataSource, businessAreaMapper)

    // Inicialización de los ViewModel
    private val signUpViewModel: SignupViewModel by viewModels {
        SignupViewModelFactory(interestRepository, collegeRepository, degreeRepository, businessAreaRepository)
    }

    private val loginViewModel : LoginViewModel by viewModels()

    // Inicialización de los repositorios de Internship
    private val internshipMapper = InternshipMapper()
    private val internshipDataSource = InternshipDataSourceImpl()
    private val internshipRepository = InternshipRepositoryImpl(internshipDataSource, internshipMapper)

    // Inicialización del InternshipViewModel
    private val internshipViewModel: InternshipViewModel by viewModels {
        InternshipViewModelFactory(internshipRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BravIATheme {
                MainScreen(
                    singUpViewModel = signUpViewModel,
                    internshipViewModel = internshipViewModel,
                    loginViewModel = loginViewModel
                )
            }
        }
    }
}


@Composable
fun MainScreen(
    singUpViewModel: SignupViewModel,
    internshipViewModel: InternshipViewModel,
    loginViewModel: LoginViewModel
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold (
        bottomBar = {
            when (currentRoute) {
                NavRoutes.Login.ROUTE,
                NavRoutes.Start.ROUTE,
                NavRoutes.LoginSaved.ROUTE,
                NavRoutes.SignIn.ROUTE,
                NavRoutes.SignUp.ROUTE,
                NavRoutes.InterestsSignUp.ROUTE,
                NavRoutes.ProfileSignUp.ROUTE,
                NavRoutes.InternshipDetail.ROUTE -> {} // Do nothing (oculta el bottom bar)
                else -> BottomNavigationBar(navController = navController)
            }
        }
    ) { paddingValues ->
        NavGraph(
            navController = navController,
            paddingValues = paddingValues,
            internshipViewModel = internshipViewModel,
            signUpViewModel = singUpViewModel,
            loginViewModel = loginViewModel
        )
    }
}


//Joshua
//class MainActivity : ComponentActivity() {
//
//    private val signUpViewModel : SignupViewModel by viewModels {
//        // Crear el mapper
//        val interestMapper = InterestMapper()
//
//        // Crear la fuente de datos
//        val dataSource = InterestDataSourceImpl()
//
//        // Crear el repositorio
//        val repository = InterestRepositoryImpl(dataSource, interestMapper)
//
//        // Crear la fábrica del ViewModel
//        SignupViewModelFactory(repository)
//    }
//    private val internshipViewModel: InternshipViewModel by viewModels {
//        // Crear el mapper
//        val internshipMapper = InternshipMapper()
//
//        // Crear la fuente de datos
//        val dataSource = InternshipDataSourceImpl()
//
//        // Crear el repositorio
//        val repository = InternshipRepositoryImpl(dataSource, internshipMapper)
//
//        // Crear la fábrica del ViewModel
//        InternshipViewModelFactory(repository)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            BravIATheme {
//                //MainScreen(internshipViewModel)
//                MainScreen(signUpViewModel, internshipViewModel)
//            }
//        }
//    }
//}
//Mau
//@Composable
//fun MainScreen(singUpviewModel: SignupViewModel, internshipViewModel: InternshipViewModel) {
//    val navController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//
//    Scaffold (
//        bottomBar = {
//
//
//            if (currentRoute != "signup" && currentRoute?.contains("profileSignup") == false && currentRoute != "interestsSignup") {
//                BottomNavigationBar(navController = navController)
//            }
//        }
//    ) { paddingValues ->
//        NavGraph(
//            navController = navController,
//            paddingValues = paddingValues,
//            internshipViewModel = internshipViewModel,
//            signUpViewModel = singUpviewModel
//        )
//    }
//}

