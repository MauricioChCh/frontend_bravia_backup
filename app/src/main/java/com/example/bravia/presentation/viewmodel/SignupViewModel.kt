package com.example.bravia.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bravia.domain.model.College
import com.example.bravia.domain.usecase.GetAllCollegesUseCase


sealed class SignUPState {
    data class Loading(val message: String) : SignUPState()
    data class Success(val message: String) : SignUPState()
    data object Empty : SignUPState()
    data class Error(val message: String) : SignUPState()
}

class SignupViewModel (
//    private val getAllInterestsUseCase : GetAllInterestUseCase,
//    private val getInterestByIdUseCase : GetInterestByIdUseCase,
    private val getAllCollegesUseCase: GetAllCollegesUseCase,
) : ViewModel() {


    var _listofCollege: List<College> = emptyList()

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


    fun findAllColleges() {
        _listofCollege = getAllCollegesUseCase()
    }

    fun getAllColleges(): List<College> {
        return _listofCollege
    }

    fun signUp() {
        // Implement sign up logic here
    }

    init {
        findAllColleges()
    }

}