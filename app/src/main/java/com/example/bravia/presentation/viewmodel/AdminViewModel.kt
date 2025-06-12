package com.example.bravia.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.Student
import com.example.bravia.domain.model.UserReport
import com.example.bravia.domain.usecase.BanUserIdUseCase
import com.example.bravia.domain.usecase.GetAdminCompanyByIdUseCase
import com.example.bravia.domain.usecase.GetAdminStudentByIdUseCase
import com.example.bravia.domain.usecase.GetAllCompaniesUseCase
import com.example.bravia.domain.usecase.GetAllStudentsUseCase
import com.example.bravia.domain.usecase.GetAllUserReportsUseCase
import com.example.bravia.domain.usecase.GetCompanyByCompanyIdUseCase
import com.example.bravia.domain.usecase.GetCompanyByIdUseCase
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
    private val getUserReportByIdUseCase: GetUserReportByIdUseCase,
    private val getCompanyByIdUseCase: GetAdminCompanyByIdUseCase,
    private val getStudentByIdUseCase: GetAdminStudentByIdUseCase,
    private val getCompanyByCompanyIdUseCase: GetCompanyByCompanyIdUseCase,
    private val banUserByIdUseCase: BanUserIdUseCase,
) : ViewModel() {

    private val _companies = MutableStateFlow<List<Company>>(emptyList())
    val companies: StateFlow<List<Company>> = _companies.asStateFlow()

    private val _company = MutableStateFlow<Company?>(null)
    val company: StateFlow<Company?> = _company

    private val _companyC = MutableStateFlow<Company?>(null)
    val companyC: StateFlow<Company?> = _companyC

    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students.asStateFlow()

    private val _student = MutableStateFlow<Student?>(null)
    val student: StateFlow<Student?> = _student

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
                    Log.d("AdminViewModel", "student -> id: ${student.id}, name: ${student.userInput?.firstName} ${student.userInput?.lastName} ")
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

    fun fetchStudentById(studentId: Long) {
        viewModelScope.launch {
            _adminState.value = AdminState.Loading
            runCatching {
                getStudentByIdUseCase(studentId)
            }.onSuccess { result ->
                val student = result.getOrNull()
                if (student != null) {
                    Log.d("AdminViewModel", "fetchStudentById - fetched student with id: ${student.id}")
                    _student.value = student
                    _adminState.value = AdminState.Success
                } else {
                    Log.w("AdminViewModel", "fetchStudentById - no student found with id: $studentId")
                    _adminState.value = AdminState.Empty
                }
            }.onFailure { throwable ->
                Log.e("AdminViewModel", "fetchStudentById - error: ${throwable.message}", throwable)
                _adminState.value = AdminState.Error(throwable.message ?: "Error fetching student")
            }
        }
    }

    fun fetchCompanyById(companyId: Long) {
        viewModelScope.launch {
            _adminState.value = AdminState.Loading
            runCatching {
                getCompanyByIdUseCase(companyId)
            }.onSuccess { result ->
                val company = result.getOrNull()
                if (company != null) {
                    Log.d("AdminViewModel", "fetchCompanyById - fetched company with id: ${company.id}")
                    _company.value = company
                    _adminState.value = AdminState.Success
                } else {
                    Log.w("AdminViewModel", "fetchCompanyById - no company found with id: $companyId")
                    _adminState.value = AdminState.Empty
                }
            }.onFailure { throwable ->
                Log.e("AdminViewModel", "fetchCompanyById - error: ${throwable.message}", throwable)
                _adminState.value = AdminState.Error(throwable.message ?: "Error fetching company")
            }
        }
    }

    fun fetchCompanyByCompanyId(companyId: Long) {
        viewModelScope.launch {
            _adminState.value = AdminState.Loading
            runCatching {
                getCompanyByCompanyIdUseCase(companyId)
            }.onSuccess { result ->
                val company = result.getOrNull()
                if (company != null) {
                    Log.d("AdminViewModel", "fetchCompanyById - fetched company with id: ${company.id}")
                    _company.value = company
                    _adminState.value = AdminState.Success
                } else {
                    Log.w("AdminViewModel", "fetchCompanyById - no company found with id: $companyId")
                    _adminState.value = AdminState.Empty
                }
            }.onFailure { throwable ->
                Log.e("AdminViewModel", "fetchCompanyById - error: ${throwable.message}", throwable)
                _adminState.value = AdminState.Error(throwable.message ?: "Error fetching company")
            }
        }
    }

    fun banUserById(userId: Long, isBanned: Boolean) {
        viewModelScope.launch {
            _adminState.value = AdminState.Loading
            runCatching {
                banUserByIdUseCase(userId, isBanned)
            }.onSuccess {
                Log.d("AdminViewModel", "banUserById - updated ban status for userId: $userId to $isBanned")
                _adminState.value = AdminState.Success
            }.onFailure { throwable ->
                Log.e("AdminViewModel", "banUserById - error: ${throwable.message}", throwable)
                _adminState.value = AdminState.Error(throwable.message ?: "Error banning user")
            }
        }
    }
}