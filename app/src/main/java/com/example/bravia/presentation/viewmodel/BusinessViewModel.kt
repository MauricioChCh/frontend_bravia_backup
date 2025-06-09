package com.example.bravia.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.model.Location
import com.example.bravia.domain.model.NewInternship
import com.example.bravia.domain.usecase.BookmarkInternshipUseCase
import com.example.bravia.domain.usecase.BusinessNewInternshipUseCase
import com.example.bravia.domain.usecase.GetAllBusinessInternshipUseCase
import com.example.bravia.domain.usecase.GetAllBusinessLocationsUseCase
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
    private val getAllBusinessLocationsUseCase: GetAllBusinessLocationsUseCase,
    private val businessNewInternshipUseCase: BusinessNewInternshipUseCase,
    private val getCompanyByIdUseCase: GetCompanyByIdUseCase,
) : ViewModel() {

    private val _company = MutableStateFlow<Company?>(null)
    val company: StateFlow<Company?> = _company.asStateFlow()

    private val _locations = MutableStateFlow<List<Location>>(emptyList())
    val locations: StateFlow<List<Location>> = _locations.asStateFlow()

    private val _businessState = MutableStateFlow<BusinessState>(BusinessState.Empty)
    val businessState: StateFlow<BusinessState> = _businessState.asStateFlow()

    private val _modalities = MutableStateFlow<List<String>>(
        listOf("Remote", "On-site", "Hybrid", "Flexible", "Part-time", "Full-time", "Internship", "Contract", "Temporary", "Volunteer")
    )
    val modalities: StateFlow<List<String>> = _modalities

    private val _internships = MutableStateFlow<List<Internship>>(emptyList())
    val internships: StateFlow<List<Internship>> = _internships.asStateFlow()

    fun fetchAllBusinessInternships(businessId: Long) {
        viewModelScope.launch {
            _businessState.value = BusinessState.Loading
            runCatching {
                getAllBusinessInternshipUseCase(businessId)
            }.onSuccess { result ->
                _internships.value = result.getOrNull() ?: emptyList()
                _businessState.value = if (result.isSuccess) {
                    BusinessState.Success
                } else {
                    BusinessState.Empty
                }
            }.onFailure { exception ->
                _businessState.value = BusinessState.Error(exception.message ?: "Failed to fetch internships")
            }
        }
    }

    fun bookmarkInternship(internshipId: Long, isBookmarked: Boolean) {
//        viewModelScope.launch {
//            _businessState.value = BusinessState.Loading
//            runCatching {
//                BookmarkInternshipUseCase(internshipId, isBookmarked)
//            }.onSuccess {
//                _businessState.value = BusinessState.Success
//            }.onFailure { exception ->
//                _businessState.value = BusinessState.Error(exception.message ?: "Failed to bookmark internship")
//            }
//        }
    }


    // This is for profile
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

    fun fetchLocations(companyId: Long) {
        viewModelScope.launch {
            _businessState.value = BusinessState.Loading
            runCatching {
                getAllBusinessLocationsUseCase(companyId)
            }.onSuccess { result ->
                _locations.value = result.getOrNull() ?: emptyList()
                _businessState.value = if (_locations.value.isNotEmpty()) {
                    BusinessState.Success
                } else {
                    BusinessState.Empty
                }
            }.onFailure { exception ->
                _businessState.value = BusinessState.Error(exception.message ?: "Failed to fetch locations")
            }
        }
    }

    fun addInternship(internship: NewInternship) {
        viewModelScope.launch {
            _businessState.value = BusinessState.Loading
            runCatching {
                businessNewInternshipUseCase(internship)
            }.onSuccess { result ->
                if (result.isSuccess) {
                    _businessState.value = BusinessState.Success
                } else {
                    _businessState.value = BusinessState.Error("Failed to add internship")
                }
            }.onFailure { exception ->
                _businessState.value = BusinessState.Error(exception.message ?: "Failed to add internship")
            }
        }
    }

}