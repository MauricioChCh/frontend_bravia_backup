package com.example.bravia.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.Student
import com.example.bravia.domain.model.UserReport
import com.example.bravia.domain.usecase.GetAllCompaniesUseCase
import com.example.bravia.domain.usecase.GetAllStudentsUseCase
import com.example.bravia.domain.usecase.GetAllUserReportsUseCase
import com.example.bravia.domain.usecase.GetUserReportByIdUseCase
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
    private val getAllStudentsUseCase: GetAllStudentsUseCase,
    private val getAllUserReportsUseCase: GetAllUserReportsUseCase,
    private val getUserReportByIdUseCase: GetUserReportByIdUseCase
) : ViewModel() {

    private val _companies = MutableStateFlow<List<Company>>(emptyList())
    val companies: StateFlow<List<Company>> = _companies.asStateFlow()

    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students.asStateFlow()

    private val _reports = MutableStateFlow<List<UserReport>>(emptyList())
    val reports: StateFlow<List<UserReport>> = _reports.asStateFlow()

    private val _report = MutableStateFlow<UserReport?>(null)
    val report: StateFlow<UserReport?> = _report

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

    fun fetchAllReports(){
        viewModelScope.launch {
            _adminState.value = AdminState.Loading
            runCatching {
                getAllUserReportsUseCase()
            }.onSuccess {
                val reportsList = it.getOrDefault(emptyList())
                Log.d("AdminViewModel", "fetchAllStudents - fetched ${reportsList.size} students")
                reportsList.forEach { userReport ->
                    Log.d("AdminViewModel", "student -> id: ${userReport.id}, ReporterName: ${userReport.reporterName}, description ${userReport.description} ")
                }
                _reports.value = reportsList
                _adminState.value = if (reportsList.isNotEmpty()) AdminState.Success else AdminState.Empty
            }.onFailure {
                Log.e("AdminViewModel", "fetchAllStudents - error: ${it.message}", it)
                _adminState.value = AdminState.Error(it.message ?: "Error fetching companies")
            }

        }
    }

    fun fetchReportById(reportId: Long) {
        viewModelScope.launch {
            _adminState.value = AdminState.Loading
            runCatching {
                getUserReportByIdUseCase(reportId)
            }.onSuccess { result ->
                val userReport = result.getOrNull()
                if (userReport != null) {
                    Log.d("AdminViewModel", "fetchReportById - fetched report with id: ${userReport.id}")
                    _report.value = userReport
                    _adminState.value = AdminState.Success
                } else {
                    Log.w("AdminViewModel", "fetchReportById - no report found with id: $reportId")
                    _adminState.value = AdminState.Empty
                }
            }.onFailure { throwable ->
                Log.e("AdminViewModel", "fetchReportById - error: ${throwable.message}", throwable)
                _adminState.value = AdminState.Error(throwable.message ?: "Error fetching report")
            }
        }
    }
}