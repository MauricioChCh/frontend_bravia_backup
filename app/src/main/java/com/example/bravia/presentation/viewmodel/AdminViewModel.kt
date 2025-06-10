package com.example.bravia.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.usecase.GetAllCompaniesUseCase
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
) : ViewModel() {

    private val _companies = MutableStateFlow<List<Company>>(emptyList())
    val companies: StateFlow<List<Company>> = _companies.asStateFlow()

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


}