package com.example.bravia.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.domain.model.Internship
import com.example.bravia.domain.usecase.BookmarkInternshipUseCase
import com.example.bravia.domain.usecase.GetAllInternshipsUseCase
import com.example.bravia.domain.usecase.GetBookmarkedInternshipsUseCase
import com.example.bravia.domain.usecase.GetInternshipByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

//clase sellada que representa los diferentes estados que puede tener
sealed class InternshipState {
    data object Loading : InternshipState()
    data class Success(val internship: Internship) : InternshipState()
    data object Empty : InternshipState()
    data class Error(val message: String) : InternshipState()
}

/**
 * ViewModel for managing internship-related operations.
 *
 * Features:
 * - StateFlow for reactive state management.
 * - Coroutine integration with viewModelScope.
 * - Separation of mutable and immutable state.
 * - Internship selection and full internship list management.
 * - Integration with domain use cases for business logic.
 */
class InternshipViewModel(
    private val getAllInternshipsUseCase: GetAllInternshipsUseCase,
    private val getInternshipByIdUseCase: GetInternshipByIdUseCase,
    private val bookmarkInternshipUseCase: BookmarkInternshipUseCase,
    private val getBookmarkedInternshipsUseCase: GetBookmarkedInternshipsUseCase
) : ViewModel() {

    // MutableStateFlow to hold the current internship state
    private val _internshipState = MutableStateFlow<InternshipState>(InternshipState.Empty)
    val internshipState: StateFlow<InternshipState> = _internshipState.asStateFlow()

    // StateFlow to hold the current internship selected by ID
    private val _selectedInternship = MutableStateFlow<Internship?>(null)
    val selectedInternship: StateFlow<Internship?> = _selectedInternship.asStateFlow()

    // StateFlow to hold the list of internships
    private val _internshipList = MutableStateFlow<List<Internship>>(emptyList())
    val internshipList: StateFlow<List<Internship>> = _internshipList.asStateFlow()

    // StateFlow to hold the list of bookmarked internships
    private val _bookmarkedInternships = MutableStateFlow<List<Internship>>(emptyList())
    val bookmarkedInternships: StateFlow<List<Internship>> = _bookmarkedInternships.asStateFlow()

    /**
     * Finds and sets the selected internship by its ID.
     *
     * @param internshipId The ID of the internship to retrieve.
     */
    fun selectInternshipById(internshipId: Long) {
        viewModelScope.launch {
            _internshipState.value = InternshipState.Loading
            val internship = getInternshipByIdUseCase(internshipId)
            if (internship != null) {
                _selectedInternship.value = internship
                _internshipState.value = InternshipState.Success(internship)
            } else {
                _internshipState.value = InternshipState.Error("Internship not found")
            }
        }
    }

    /**
     * Retrieves a random internship from the available internships.
     *
     * Steps:
     * 1. Sets the internship state to Loading.
     * 2. Gets all internships if the list is empty
     * 3. Selects a random internship from the list
     * 4. Updates the internship state and selectedInternship.
     */
    fun getRandomInternship() {
        viewModelScope.launch {
            _internshipState.value = InternshipState.Loading

            // Ensure we have internships to choose from
            if (_internshipList.value.isEmpty()) {
                findAllInternships()
            }

            if (_internshipList.value.isNotEmpty()) {
                val randomIndex = (0 until _internshipList.value.size).random()
                val internship = _internshipList.value[randomIndex]
                _selectedInternship.value = internship
                _internshipState.value = InternshipState.Success(internship)
            } else {
                _internshipState.value = InternshipState.Empty
            }
        }
    }

    /**
     * Retrieves all available internships.
     *
     * This function:
     * 1. Fetches the complete internship list using the use case.
     * 2. Updates the internshipList state with the retrieved data.
     */
    fun findAllInternships() {
        viewModelScope.launch {
            val internships = getAllInternshipsUseCase()
            _internshipList.value = internships
        }
    }

    /**
     * Toggles the bookmark status of an internship.
     *
     * @param id The ID of the internship to bookmark/unbookmark
     * @param isBookmarked The new bookmark status
     */
    fun bookmarkInternship(id: Long, isBookmarked: Boolean) {
        viewModelScope.launch {
            bookmarkInternshipUseCase(id, isBookmarked)

            // Refresh lists to reflect the change
            findAllInternships()
            loadBookmarkedInternships()

            // Update selected internship if it's the one being bookmarked
            _selectedInternship.value?.let { internship ->
                if (internship.id == id) {
                    selectInternshipById(id)
                }
            }
        }
    }

    /**
     * Loads all bookmarked internships.
     */
    fun loadBookmarkedInternships() {
        viewModelScope.launch {
            _bookmarkedInternships.value = getBookmarkedInternshipsUseCase()
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

            val allInternships = getAllInternshipsUseCase()
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
        loadBookmarkedInternships()
    }
}