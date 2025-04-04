package com.example.bravia.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.domain.model.BusinessArea
import com.example.bravia.domain.model.College
import com.example.bravia.domain.model.Degree
import com.example.bravia.domain.model.Interest
import com.example.bravia.domain.usecase.GetAllBusinessAreaUseCase
import com.example.bravia.domain.usecase.GetAllCollegesUseCase
import com.example.bravia.domain.usecase.GetAllDegreesUseCase
import com.example.bravia.domain.usecase.GetAllInterestUseCase
import com.example.bravia.domain.usecase.GetInterestByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class SignUPState {
    data class Loading(val message: String) : SignUPState()
    data class Success(val message: String) : SignUPState()
    data object Empty : SignUPState()
    data class Error(val message: String) : SignUPState()
}


@HiltViewModel
class SignupViewModel @Inject constructor(
    private val getAllInterestsUseCase : GetAllInterestUseCase,
    private val getInterestByIdUseCase : GetInterestByIdUseCase,
    private val getAllCollegesUseCase: GetAllCollegesUseCase,
    private val getAllDegreesUseCase: GetAllDegreesUseCase,
    private val getAllBusinessAreasUseCase: GetAllBusinessAreaUseCase
) : ViewModel() {


    var _listofCollege: List<College> = emptyList()
    var _listofDegree: List<Degree> = emptyList()
    var _listofBusinessArea: List<BusinessArea> = emptyList()
    var _listofInterest: List<Interest> = emptyList()
        private set

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
    fun onInterestsChange(interests: List<String>) {
        this.interests = interests
    }
    fun onInterestsChange(interest: String) {
        this.interests = interests + interest
    }
    fun onInterestsRemove(interest: String) {
        this.interests = interests - interest
    }
    fun onInterestsClear() {
        this.interests = emptyList()
    }


    suspend fun findAllColleges() {
        _listofCollege = getAllCollegesUseCase().getOrNull() ?: emptyList()
    }
    suspend fun findAllDegrees() {
        _listofDegree = getAllDegreesUseCase().getOrNull() ?: emptyList()
    }
    suspend fun findAllBusinessAreas() {
        _listofBusinessArea = getAllBusinessAreasUseCase().getOrNull() ?: emptyList()
    }
    suspend fun findAllInterests() {
        _listofInterest = getAllInterestsUseCase().getOrNull() ?: emptyList()
    }

    fun getAllColleges(): List<College> {
        return _listofCollege
    }
    fun getAllDegrees(): List<Degree> {
        return _listofDegree
    }
    fun getAllBusinessAreas(): List<BusinessArea> {
        return _listofBusinessArea
    }
    fun getAllInterests(): List<Interest> {
        return _listofInterest
    }

    fun findStudentLists() {
        if (_listofCollege.isEmpty() || _listofDegree.isEmpty()) {
            viewModelScope.launch {
                findAllColleges()
                findAllDegrees()
            }
        }
    }

    fun findBusinessAreaLists() {
        if (_listofBusinessArea.isEmpty()) {
            viewModelScope.launch {
                findAllBusinessAreas()
            }
        }
    }

    fun signUp() {
        // TODO: Implement sign up logic here
    }


}