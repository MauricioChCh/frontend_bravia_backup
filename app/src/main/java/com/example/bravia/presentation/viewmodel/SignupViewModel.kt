package com.example.bravia.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.domain.model.BusinessArea
import com.example.bravia.domain.model.College
import com.example.bravia.domain.model.CompanyNew
import com.example.bravia.domain.model.Degree
import com.example.bravia.domain.model.Interest
import com.example.bravia.domain.model.StudentNew
import com.example.bravia.domain.usecase.GetAllBusinessAreaUseCase
import com.example.bravia.domain.usecase.GetAllCollegesUseCase
import com.example.bravia.domain.usecase.GetAllDegreesUseCase
import com.example.bravia.domain.usecase.GetAllInterestUseCase
import com.example.bravia.domain.usecase.GetInterestByIdUseCase
import com.example.bravia.domain.usecase.RegisterBusinessUseCase
import com.example.bravia.domain.usecase.RegisterStudentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SignUpState {
    data object Initial : SignUpState()
    data object Loading : SignUpState()
    data class Success(val message: String) : SignUpState()
    data object Empty : SignUpState()
    data class Error(val message: String) : SignUpState()
}

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val getAllInterestsUseCase: GetAllInterestUseCase,
    private val getInterestByIdUseCase: GetInterestByIdUseCase,
    private val getAllCollegesUseCase: GetAllCollegesUseCase,
    private val getAllDegreesUseCase: GetAllDegreesUseCase,
    private val getAllBusinessAreasUseCase: GetAllBusinessAreaUseCase,
    private val registerBusinessUseCase: RegisterBusinessUseCase,
    private val registerStudentUseCase: RegisterStudentUseCase,
) : ViewModel() {

    private val _listOfCollege = MutableStateFlow<List<College>>(emptyList())
    val listOfCollege = _listOfCollege.asStateFlow()

    private val _listOfDegree = MutableStateFlow<List<Degree>>(emptyList())
    val listOfDegree = _listOfDegree.asStateFlow()

    private val _listOfBusinessArea = MutableStateFlow<List<BusinessArea>>(emptyList())
    val listOfBusinessArea = _listOfBusinessArea.asStateFlow()

    private val _listOfInterest = MutableStateFlow<List<Interest>>(emptyList())
    val listOfInterest = _listOfInterest.asStateFlow()

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Initial)
    val signUpState = _signUpState.asStateFlow()

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set
    var firstName by mutableStateOf("")
        private set
    var lastName by mutableStateOf("")
        private set
    var college by mutableStateOf("")
        private set
    var degree by mutableStateOf("")
        private set
    var companyName by mutableStateOf("")
        private set
    var businessArea by mutableStateOf("")
        private set
    var interests by mutableStateOf<List<String>>(emptyList())
        private set

    private var collegesLoaded = false
    private var degreesLoaded = false
    private var businessAreasLoaded = false
    private var interestsLoaded = false

    fun onEmailChange(email: String) {
        this.email = email
    }

    fun onPasswordChange(password: String) {
        this.password = password
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        this.confirmPassword = confirmPassword
    }

    fun onFirstNameChange(firstName: String) {
        this.firstName = firstName
    }

    fun onLastNameChange(lastName: String) {
        this.lastName = lastName
    }

    fun onCollegeChange(college: String) {
        this.college = college
    }

    fun onDegreeChange(degree: String) {
        this.degree = degree
    }

    fun onCompanyNameChange(companyName: String) {
        this.companyName = companyName
    }

    fun onBusinessAreaChange(businessArea: String) {
        this.businessArea = businessArea
    }

    fun findAllColleges() {
        if (!collegesLoaded) {
            viewModelScope.launch {
                _signUpState.value = SignUpState.Loading
                _listOfCollege.value = emptyList()
                runCatching {
                    getAllCollegesUseCase()
                }.onSuccess { result ->
                    _listOfCollege.value = result.getOrNull() ?: emptyList()
                    _signUpState.value = SignUpState.Success("Colleges loaded successfully")
                    collegesLoaded = true
                }.onFailure { exception ->
                    _signUpState.value = SignUpState.Error(exception.message ?: "Unknown error")
                    Log.e("SignupViewModel", "Error fetching colleges: ${exception.message}")
                }
            }
        }
    }

    fun findAllDegrees() {
        if (!degreesLoaded) {
            viewModelScope.launch {
                _signUpState.value = SignUpState.Loading
                runCatching {
                    getAllDegreesUseCase()
                }.onSuccess { result ->
                    _listOfDegree.value = result.getOrNull() ?: emptyList()
                    _signUpState.value = SignUpState.Success("Degrees loaded successfully")
                    degreesLoaded = true
                }.onFailure { exception ->
                    _signUpState.value = SignUpState.Error(exception.message ?: "Unknown error")
                    Log.e("SignupViewModel", "Error fetching degrees: ${exception.message}")
                }
            }
        }
    }

    fun findAllBusinessAreas() {
        if (!businessAreasLoaded) {
            viewModelScope.launch {
                _signUpState.value = SignUpState.Loading
                runCatching {
                    getAllBusinessAreasUseCase()
                }.onSuccess { result ->
                    _listOfBusinessArea.value = result.getOrNull() ?: emptyList()
                    _signUpState.value = SignUpState.Success("Business areas loaded successfully")
                    businessAreasLoaded = true
                }.onFailure { exception ->
                    _signUpState.value = SignUpState.Error(exception.message ?: "Unknown error")
                    Log.e("SignupViewModel", "Error fetching business areas: ${exception.message}")
                }
            }
        }
    }

    fun findAllInterests() {
        if (!interestsLoaded) {
            viewModelScope.launch {
                _signUpState.value = SignUpState.Loading
                runCatching {
                    getAllInterestsUseCase()
                }.onSuccess { result ->
                    _listOfInterest.value = result.getOrNull() ?: emptyList()
                    _signUpState.value = SignUpState.Success("Interests loaded successfully")
                    interestsLoaded = true
                }.onFailure { exception ->
                    _signUpState.value = SignUpState.Error(exception.message ?: "Unknown error")
                    Log.e("SignupViewModel", "Error fetching interests: ${exception.message}")
                }
            }
        }
    }

    fun registerBusiness () {
        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank() ||
            firstName.isBlank() || lastName.isBlank() || companyName.isBlank() ||
            businessArea.isBlank() ) {
            _signUpState.value = SignUpState.Empty
            return
        }

        _signUpState.value = SignUpState.Loading

        viewModelScope.launch {
            runCatching {
                registerBusinessUseCase(
                    CompanyNew(
                        email = email,
                        password = password,
                        firstName = firstName,
                        lastName = lastName,
                        name = companyName,
                        businessArea = listOfBusinessArea.value.find { it.name == businessArea }!!
                    )
                )
            }.onSuccess {
                // TODO: Handle success, e.g., navigate to a different screen or show a success message
                _signUpState.value = SignUpState.Success("Registration successful")
            }.onFailure {
                // TODO: Handle failure, e.g., show an error message
                _signUpState.value = SignUpState.Error(it.message ?: "Unknown error")
                Log.e("SignupViewModel", "Error during registration: ${it.message}")
            }
        }
    }

    fun registerStudent() {

        if (email.isBlank()
            || password.isBlank()
            || confirmPassword.isBlank()
            || firstName.isBlank()
            || lastName.isBlank()
            || college.isBlank()
            || degree.isBlank()
            || interests.isEmpty()) {
            _signUpState.value = SignUpState.Empty
            return
        }

        _signUpState.value = SignUpState.Loading

        viewModelScope.launch {
            runCatching {
                registerStudentUseCase(
                    StudentNew(
                        email = email,
                        password = password,
                        confirmPassword = confirmPassword,
                        firstName = firstName,
                        lastName = lastName,
                        college = listOfCollege.value.find { it.name == college }!!,
                        degree = listOfDegree.value.find { it.name == degree }!!,
                        interest = interests.mapNotNull { interestName ->
                            listOfInterest.value.find { it.name == interestName }
                        }
                    )
                )
            }.onSuccess {
                _signUpState.value = SignUpState.Success("Registration successful")
            }.onFailure {
                _signUpState.value = SignUpState.Error(it.message ?: "Unknown error")
                Log.e("SignupViewModel", "Error during registration: ${it.message}")
            }
        }
    }

    fun updateSelectedInterests(selected: Set<Interest>) {
        interests = selected.map { it.name }
    }

}