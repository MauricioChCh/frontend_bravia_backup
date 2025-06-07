package com.example.bravia.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.usecase.BookmarkInternshipUseCase
import com.example.bravia.domain.usecase.GetAllBusinessInternshipUseCase
import com.example.bravia.domain.usecase.GetBookmarkedInternshipsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.bravia.domain.usecase.GetBusinessInternshipByIdUseCase
import com.example.bravia.domain.usecase.GetCompanyByIdUseCase

sealed class BusinessState {
    data object Loading : BusinessState()
    data object Success : BusinessState()
    data object Empty : BusinessState()
    data class Error(val message: String) : BusinessState()
}


@HiltViewModel
class BusinessViewModel @Inject constructor(
    private val getAllBusinessInternshipUseCase: GetAllBusinessInternshipUseCase,
    private val getBusinessInternshipByIdUseCase: GetBusinessInternshipByIdUseCase,
    private val bookmarkInternshipUseCase: BookmarkInternshipUseCase,
    private val getBookmarkedInternshipsUseCase: GetBookmarkedInternshipsUseCase,
    private val getCompanyByIdUseCase: GetCompanyByIdUseCase,
) : ViewModel() {

    private val _company = MutableStateFlow<Company?>(null)
    val company: StateFlow<Company?> = _company.asStateFlow()

    private val _businessState = MutableStateFlow<BusinessState>(BusinessState.Empty)
    val businessState: StateFlow<BusinessState> = _businessState.asStateFlow()

    fun fetchCompanyById(companyId: Long) {
        viewModelScope.launch {
            _businessState.value = BusinessState.Loading
            runCatching {
                getCompanyByIdUseCase(companyId)
            }.onSuccess { result ->
                _company.value = result.getOrNull()
                _businessState.value = if (_company.value != null) {
                    BusinessState.Success
                } else {
                    BusinessState.Empty
                }
            }.onFailure { exception ->
                _businessState.value = BusinessState.Error(exception.message ?: "Failed to fetch company")
            }
        }
    }
}