package com.example.bravia.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.data.local.AuthPreferences
import com.example.bravia.domain.model.BusinessArea
import com.example.bravia.domain.model.City
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.Country
import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.model.Location
import com.example.bravia.domain.model.Modality
import com.example.bravia.domain.model.NewInternship
import com.example.bravia.domain.model.Tag
import com.example.bravia.domain.model.UpdateInternship
import com.example.bravia.domain.usecase.BookmarkInternshipUseCase
import com.example.bravia.domain.usecase.BusinessNewInternshipUseCase
import com.example.bravia.domain.usecase.BusinessUpdateInternshipUseCase
import com.example.bravia.domain.usecase.GetAllBusinessAreaUseCase
import com.example.bravia.domain.usecase.GetAllBusinessInternshipUseCase
import com.example.bravia.domain.usecase.GetAllBusinessLocationsUseCase
import com.example.bravia.domain.usecase.GetAllCitiesUseCase
import com.example.bravia.domain.usecase.GetAllCountriesUseCase
import com.example.bravia.domain.usecase.GetAllInternshipModalitiesUseCase
import com.example.bravia.domain.usecase.GetAllTagsUseCase
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
    private val bookmarkInternshipUseCase: BookmarkInternshipUseCase,
    private val getAllBusinessLocationsUseCase: GetAllBusinessLocationsUseCase,
    private val getAllInternshipModalitiesUseCase: GetAllInternshipModalitiesUseCase,
    private val businessNewInternshipUseCase: BusinessNewInternshipUseCase,
    private val getCompanyByIdUseCase: GetCompanyByIdUseCase,
    private val getBusinessInternshipByIdUseCase: GetBusinessInternshipByIdUseCase,
    private val getBookmarkedInternshipsUseCase: GetBookmarkedInternshipsUseCase,
    private val businessUpdateInternshipUseCase: BusinessUpdateInternshipUseCase,
    private val getAllCitiesUseCase: GetAllCitiesUseCase,
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val getAllBusinessAreasUseCase: GetAllBusinessAreaUseCase,
    private val getAllTagsUseCase: GetAllTagsUseCase,
    private val authPreferences: AuthPreferences,
) : ViewModel() {

    private val _company = MutableStateFlow<Company?>(null)
    val company: StateFlow<Company?> = _company.asStateFlow()

    private val _internship = MutableStateFlow<Internship?>(null)
    val internship: StateFlow<Internship?> = _internship.asStateFlow()

    private val _locations = MutableStateFlow<List<Location>>(emptyList())
    val locations: StateFlow<List<Location>> = _locations.asStateFlow()

    private val _businessState = MutableStateFlow<BusinessState>(BusinessState.Empty)
    val businessState: StateFlow<BusinessState> = _businessState.asStateFlow()

    private val _modalities = MutableStateFlow<List<Modality>>(emptyList())
    val modalities: StateFlow<List<Modality>> = _modalities.asStateFlow()

    private val _internships = MutableStateFlow<List<Internship>>(emptyList())
    val internships: StateFlow<List<Internship>> = _internships.asStateFlow()

    private val _bookmarkedInternships = MutableStateFlow<List<Internship>>(emptyList())
    val bookmarkedInternships: StateFlow<List<Internship>> = _bookmarkedInternships.asStateFlow()

    private val _cities = MutableStateFlow<List<City>>(emptyList())
    val cities: StateFlow<List<City>> = _cities.asStateFlow()

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries.asStateFlow()

    private val _businessAreas = MutableStateFlow<List<BusinessArea>>(emptyList())
    val businessAreas: StateFlow<List<BusinessArea>> = _businessAreas.asStateFlow()

    private val _tags = MutableStateFlow<List<Tag>>(emptyList())
    val tags: StateFlow<List<Tag>> = _tags.asStateFlow()

    fun fetchAllBookmarkedInternships() {
        viewModelScope.launch {
            _businessState.value = BusinessState.Loading
            runCatching {
                getBookmarkedInternshipsUseCase(authPreferences.getUsername()!!)
            }.onSuccess { result ->
                _bookmarkedInternships.value = result.getOrNull() ?: emptyList()
                _businessState.value = if (result.isSuccess) {
                    BusinessState.Success
                } else {
                    BusinessState.Empty
                }
            }.onFailure { exception ->
                _businessState.value = BusinessState.Error(exception.message ?: "Failed to fetch bookmarked internships")
            }
        }
    }

    fun fetchAllBusinessInternships() {
        viewModelScope.launch {
            _businessState.value = BusinessState.Loading
            runCatching {
                getAllBusinessInternshipUseCase(authPreferences.getUsername()!!)
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
        viewModelScope.launch {
            _businessState.value = BusinessState.Loading
            runCatching {
                bookmarkInternshipUseCase(internshipId, authPreferences.getUsername()!!, isBookmarked)
                fetchAllBusinessInternships()
            }.onSuccess {
                _businessState.value = BusinessState.Success
            }.onFailure { exception ->
                _businessState.value = BusinessState.Error(exception.message ?: "Failed to bookmark internship")
            }
        }
    }

    fun selectedBusinessInternshipById(internshipId: Long) {
        viewModelScope.launch {
            _businessState.value = BusinessState.Loading
            runCatching {
                getBusinessInternshipByIdUseCase( authPreferences.getUsername()!!, internshipId )
            }.onSuccess { result ->
                _internship.value = result.getOrNull()
                _businessState.value = if (_internship.value != null) {
                    BusinessState.Success
                } else {
                    BusinessState.Empty
                }
            }.onFailure { exception ->
                _businessState.value = BusinessState.Error(exception.message ?: "Failed to fetch internship")
            }
        }
    }


    // This is for profile
    fun fetchCompanyById() {
        viewModelScope.launch {
            _businessState.value = BusinessState.Loading
            runCatching {
                getCompanyByIdUseCase(authPreferences.getUsername()!!)
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


    fun fetchLocations() {
        viewModelScope.launch {
            _businessState.value = BusinessState.Loading
            runCatching {
                getAllBusinessLocationsUseCase(authPreferences.getUsername()!!)
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

    fun fetchModalities() {
        viewModelScope.launch {
            _businessState.value = BusinessState.Loading
            runCatching {
                getAllInternshipModalitiesUseCase()
            }.onSuccess { result ->
                _modalities.value = result.getOrNull() ?: emptyList()
                _businessState.value = if (_modalities.value.isNotEmpty()) {
                    BusinessState.Success
                } else {
                    BusinessState.Empty
                }
            }.onFailure { exception ->
                _businessState.value = BusinessState.Error(exception.message ?: "Failed to fetch modalities")
            }
        }
    }

    fun fetchCities() {
        viewModelScope.launch {
            _businessState.value = BusinessState.Loading
            runCatching {
                getAllCitiesUseCase()
            }.onSuccess { result ->
                _cities.value = result.getOrNull()?.map { it }?.distinct() ?: emptyList()
                _businessState.value = if (_cities.value.isNotEmpty()) {
                    BusinessState.Success
                } else {
                    BusinessState.Empty
                }
            }.onFailure { exception ->
                _businessState.value = BusinessState.Error(exception.message ?: "Failed to fetch cities")
            }
        }
    }

    fun fetchCountries() {
        viewModelScope.launch {
            _businessState.value = BusinessState.Loading
            runCatching {
                getAllCountriesUseCase()
            }.onSuccess { result ->
                _countries.value = result.getOrNull()?.map { it }?.distinct() ?: emptyList()
                _businessState.value = if (_countries.value.isNotEmpty()) {
                    BusinessState.Success
                } else {
                    BusinessState.Empty
                }
            }.onFailure { exception ->
                _businessState.value = BusinessState.Error(exception.message ?: "Failed to fetch countries")
            }
        }
    }

    fun fetchBusinessAreas() {
        viewModelScope.launch {
            _businessState.value = BusinessState.Loading
            runCatching {
                getAllBusinessAreasUseCase()
            }.onSuccess { result ->
                _businessAreas.value = result.getOrNull()?.map { it }?.distinct() ?: emptyList()
                _businessState.value = if (_businessAreas.value.isNotEmpty()) {
                    BusinessState.Success
                } else {
                    BusinessState.Empty
                }
            }.onFailure { exception ->
                _businessState.value = BusinessState.Error(exception.message ?: "Failed to fetch business areas")
            }
        }
    }

    fun fetchTags() {
        viewModelScope.launch {
            _businessState.value = BusinessState.Loading
            runCatching {
                getAllTagsUseCase()
            }.onSuccess { result ->
                _tags.value = result.getOrNull()?.map { it }?.distinct() ?: emptyList()
                _businessState.value = if (_tags.value.isNotEmpty()) {
                    BusinessState.Success
                } else {
                    BusinessState.Empty
                }
            }.onFailure { exception ->
                _businessState.value = BusinessState.Error(exception.message ?: "Failed to fetch tags")
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

    fun updateInternship(internship: UpdateInternship) {
        viewModelScope.launch {
            _businessState.value = BusinessState.Loading
            runCatching {
                businessUpdateInternshipUseCase(authPreferences.getUsername()!!, internship)
            }.onSuccess { result ->
                if (result.isSuccess) {
                    _businessState.value = BusinessState.Success
                } else {
                    _businessState.value = BusinessState.Error("Failed to update internship")
                }
            }.onFailure { exception ->
                _businessState.value = BusinessState.Error(exception.message ?: "Failed to update internship")
            }
        }
    }

}