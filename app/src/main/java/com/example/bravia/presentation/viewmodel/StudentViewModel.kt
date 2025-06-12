
package com.example.bravia.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.data.local.AuthPreferences
import com.example.bravia.domain.model.Student
import com.example.bravia.domain.model.StudentProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.bravia.domain.usecase.GetStudentByUsernameUseCase

sealed class StudentState {
    data object Loading : StudentState()
    data object Success : StudentState()
    data object Empty : StudentState()
    data class Error(val message: String) : StudentState()
}

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val getStudentByIdUseCase: GetStudentByUsernameUseCase,
    private val authPreferences: AuthPreferences,
) : ViewModel() {

    private val _student = MutableStateFlow<StudentProfile?>(null)
    val student: StateFlow<StudentProfile?> = _student.asStateFlow()

    private val _studentState = MutableStateFlow<StudentState>(StudentState.Empty)
    val studentState: StateFlow<StudentState> = _studentState.asStateFlow()

    fun fetchStudentById() {
        viewModelScope.launch {
            _studentState.value = StudentState.Loading
            runCatching {
                getStudentByIdUseCase(authPreferences.getUsername()!!)
            }.onSuccess { result ->
                _student.value = result.getOrNull() as StudentProfile?
                _studentState.value = if (_student.value != null) {
                    StudentState.Success
                } else {
                    StudentState.Empty
                }
            }.onFailure { exception ->
                _studentState.value = StudentState.Error(exception.message ?: "Failed to fetch student")
            }
        }
    }
}