package com.example.bravia.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.Student
import com.example.bravia.domain.usecase.GetAllCompaniesUseCase
import com.example.bravia.domain.usecase.GetAllStudentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AdminState {
    data object Loading : AdminState()
    data object Success : AdminState()
    data object Empty : AdminState()
    data class Error(val message: String) : AdminState()
}

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val getAllCompaniesUseCase: GetAllCompaniesUseCase,
    private val getAllStudentsUseCase: GetAllStudentsUseCase
) : ViewModel() {

    private val _companies = MutableStateFlow<List<Company>>(emptyList())
    val companies: StateFlow<List<Company>> = _companies.asStateFlow()

    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students.asStateFlow()

    private val _adminState = MutableStateFlow<AdminState>(AdminState.Empty)
    val adminState: StateFlow<AdminState> = _adminState.asStateFlow()



    fun fetchAllCompanies() {
        viewModelScope.launch {
            _adminState.value = AdminState.Loading
            runCatching {
                getAllCompaniesUseCase()
            }.onSuccess {
                val companiesList = it.getOrDefault(emptyList())
                Log.d("BusinessViewModel", "fetchAllCompanies - fetched ${companiesList.size} companies")
                companiesList.forEach { company ->
                    Log.d("BusinessViewModel", "Company -> id: ${company.id}, name: ${company.name}")
                }
                _companies.value = companiesList
                _adminState.value = if (companiesList.isNotEmpty()) AdminState.Success else AdminState.Empty
            }.onFailure {
                Log.e("BusinessViewModel", "fetchAllCompanies - error: ${it.message}", it)
                _adminState.value = AdminState.Error(it.message ?: "Error fetching companies")
            }
        }
    }

    fun fetchAllStudents(){
        viewModelScope.launch {
            _adminState.value = AdminState.Loading
            runCatching {
                getAllStudentsUseCase()
            }.onSuccess {
                val studentsList = it.getOrDefault(emptyList())
                Log.d("AdminViewModel", "fetchAllStudents - fetched ${studentsList.size} students")
                studentsList.forEach { student ->
                    Log.d("AdminViewModel", "student -> id: ${student.id}, name: ${student.userInput.firstName} ${student.userInput.lastName} ")
                }
                _students.value = studentsList
                _adminState.value = if (studentsList.isNotEmpty()) AdminState.Success else AdminState.Empty
            }.onFailure {
                Log.e("AdminViewModel", "fetchAllStudents - error: ${it.message}", it)
                _adminState.value = AdminState.Error(it.message ?: "Error fetching companies")
            }

        }
    }

}