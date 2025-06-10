package com.example.bravia.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.model.Location
import com.example.bravia.domain.model.NewInternship
import com.example.bravia.domain.usecase.BusinessNewInternshipUseCase
import com.example.bravia.domain.usecase.GetAllBusinessInternshipUseCase
import com.example.bravia.domain.usecase.GetAllBusinessLocationsUseCase
import com.example.bravia.domain.usecase.GetAllCompaniesUseCase
import com.example.bravia.domain.usecase.GetCompanyByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AdminState {
    data object Loading : BusinessState()
    data object Success : BusinessState()
    data object Empty : BusinessState()
    data class Error(val message: String) : BusinessState()
}

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val getAllBusinessLocationsUseCase: GetAllBusinessLocationsUseCase,
    private val getCompanyByIdUseCase: GetCompanyByIdUseCase,
    private val getAllCompaniesUseCase: GetAllCompaniesUseCase,
) : ViewModel() {

    private val _companies = MutableStateFlow<List<Company>>(emptyList())
    val companies: StateFlow<List<Company>> = _companies.asStateFlow()

    private val _adminState = MutableStateFlow<BusinessState>(BusinessState.Empty)
    val adminState: StateFlow<BusinessState> = _adminState.asStateFlow()



    fun fetchAllCompanies() {
        viewModelScope.launch {
            _adminState.value = BusinessState.Loading
            runCatching {
                getAllCompaniesUseCase()
            }.onSuccess {
                val companiesList = it.getOrDefault(emptyList())
                Log.d("BusinessViewModel", "fetchAllCompanies - fetched ${companiesList.size} companies")
                companiesList.forEach { company ->
                    Log.d("BusinessViewModel", "Company -> id: ${company.id}, name: ${company.name}")
                }
                _companies.value = companiesList
                _adminState.value = if (companiesList.isNotEmpty()) BusinessState.Success else BusinessState.Empty
            }.onFailure {
                Log.e("BusinessViewModel", "fetchAllCompanies - error: ${it.message}", it)
                _adminState.value = BusinessState.Error(it.message ?: "Error fetching companies")
            }
        }
    }


}