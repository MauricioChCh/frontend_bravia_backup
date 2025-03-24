package com.example.bravia.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.data.datasource.InternshipsProvider
import com.example.bravia.data.model.Internship
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//clase sellada que representa los diferentes estados que puede tener
//Usar esta clase sellada permite manejar de forma segura los diferentes estados en la UI mediante pattern matching.


sealed class InternshipState {
    data object Loading : InternshipState()
    data class Success(val interships: Internship) : InternshipState()
    data object Empty : InternshipState()
    //data class Error(val exception: Exception) : InternshipState()
}


/**
 * ViewModel for managing internship-related operations.
 *
 * Features:
 * - StateFlow for reactive state management.
 * - Coroutine integration with viewModelScope.
 * - Separation of mutable and immutable state.
 * - Internship selection and full internship list management.
 */
class InternshipViewModel : ViewModel() {

    //Dos tipos de flows para cada propiedad, uno mutable y un state flow inmutable desde fuera
    // MutableStateFlow to hold the current internship state
    private val _internship = MutableStateFlow<InternshipState>(InternshipState.Empty)
    val internship: StateFlow<InternshipState> get() = _internship

    // StateFlow to hold the current internship selected by ID
    private val _selectedInternship = MutableStateFlow<Internship?>(null)
    val selectedInternship: StateFlow<Internship?> get() = _selectedInternship

    // StateFlow to hold the list of internships
    private val _internshipList = MutableStateFlow<List<Internship>>(emptyList())
    val internshipList: StateFlow<List<Internship>> get() = _internshipList

    /**
     * Finds and sets the selected internship by its ID.
     *
     * @param internshipId The ID of the internship to retrieve.
     */
    fun selectInternshipById(internshipId: Long) {
        viewModelScope.launch {
            val internship = _internshipList.value.find { it.id == internshipId }
                ?: InternshipsProvider.findInternshipById(internshipId)
            _selectedInternship.value = internship
        }
    }

    /**
     * Retrieves a random internship from the InternshipsProvider.
     *
     * Steps:
     * 1. Sets the internship state to Loading.
     * 2. Generates a random internship ID (from the available IDs).
     * 3. Retrieves the internship from the provider.
     * 4. Updates the internship state and selectedInternship.
     */
    fun getRandomInternship() {
        viewModelScope.launch {
            _internship.value = InternshipState.Loading
            val maxId = 3L // Based on the number of internships in the provider
            val position = (1L..maxId).random()
            val internship = InternshipsProvider.findInternshipById(position)
            _internship.value = internship?.let { InternshipState.Success(it) } ?: InternshipState.Empty
            _selectedInternship.value = internship
        }
    }

    /**
     * Retrieves all available internships from the InternshipsProvider.
     *
     * This function:
     * 1. Fetches the complete internship list.
     * 2. Updates the internshipList state with the retrieved data.
     */
    fun findAllInternships() {
        viewModelScope.launch {
            val internshipList = InternshipsProvider.findAllInternships()
            _internshipList.value = internshipList
        }
    }

    /**
     * Filters internships based on search query.
     * Searches in title, company, and location fields.
     *
     * @param query The search query string
     */
    fun searchInternships(query: String) {
        viewModelScope.launch {
            if (query.isEmpty()) {
                findAllInternships()
                return@launch
            }

            val allInternships = InternshipsProvider.findAllInternships()
            val filteredList = allInternships.filter { internship ->
                internship.title.contains(query, ignoreCase = true) ||
                        internship.company.contains(query, ignoreCase = true) ||
                        internship.location.contains(query, ignoreCase = true)
            }

            _internshipList.value = filteredList
        }
    }

    /**
     * Initializes the ViewModel by loading all internships.
     */
    init {
        findAllInternships()
    }
}