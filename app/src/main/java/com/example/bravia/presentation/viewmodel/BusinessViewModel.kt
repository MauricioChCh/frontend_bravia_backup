package com.example.bravia.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

sealed class BusinessState {
    data object Loading : BusinessState()
    data class Success(val internship: Internship) : BusinessState()
    data object Empty : BusinessState()
    data class Error(val message: String) : BusinessState()
}


@HiltViewModel
class BusinessViewModel @Inject constructor(
    private val getAllBusinessInternshipUseCase: GetAllBusinessInternshipUseCase,
    private val getBusinessInternshipByIdUseCase: GetBusinessInternshipByIdUseCase,
    private val bookmarkInternshipUseCase: BookmarkInternshipUseCase,
    private val getBookmarkedInternshipsUseCase: GetBookmarkedInternshipsUseCase

): ViewModel() {
    // MutableStateFlow to hold the current internship state
    private val _internshipState = MutableStateFlow<InternshipState>(InternshipState.Empty)
    val internshipState: StateFlow<InternshipState> = _internshipState.asStateFlow()

    // StateFlow to hold the current internship selected by ID
    private val _selectedInternship = MutableStateFlow<Internship?>(null)
    val selectedInternship: StateFlow<Internship?> = _selectedInternship.asStateFlow()

    // StateFlow to hold the list of internships
    private val _internshipList = MutableStateFlow<List<Internship>>(emptyList())
    val  internshipList: StateFlow<List<Internship>> = _internshipList.asStateFlow()

    // StateFlow to hold the list of bookmarked internships
    private val _bookmarkedInternships = MutableStateFlow<List<Internship>>(emptyList())
    val bookmarkedInternships: StateFlow<List<Internship>> = _bookmarkedInternships.asStateFlow()

    //StateFlow to hold the list of applied internships
    private val _appliedInternships = MutableStateFlow<List<Internship>>(emptyList())
    val appliedInternships: StateFlow<List<Internship>> = _appliedInternships.asStateFlow()

    fun findAllBusinessOwnerIntership(businessID: Long){
        viewModelScope.launch {
            getAllBusinessInternshipUseCase(businessID)
            .onSuccess {
                _internshipList.value = it
            }
            .onFailure {
                _internshipState.value = InternshipState.Error("Error loading business internship: ${it.message}")
            }
        }
    }

    fun selectBusinessInternshipById(internshipId: Long){
        viewModelScope.launch {
            _internshipState.value = InternshipState.Loading
            getBusinessInternshipByIdUseCase(internshipId).onSuccess { internship ->
                if (internship != null) {
                    _selectedInternship.value = internship
                    _internshipState.value = InternshipState.Success(internship)
                } else {
                    _internshipState.value = InternshipState.Error("Internship not found")
                }
            }.onFailure {
                _internshipState.value = InternshipState.Error("Error: ${it.message}")
            }
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
            try {
                bookmarkInternshipUseCase(id, isBookmarked)
                findAllBusinessOwnerIntership(101) // TODO: Cambiar por una variable
                loadBookmarkedInternships()

                _selectedInternship.value?.let {
                    if (it.id == id) selectBusinessInternshipById(id)
                }
            } catch (e: Exception) {
                _internshipState.value = InternshipState.Error("Bookmark failed: ${e.message}")
            }
        }
    }

    /**
     * Loads all bookmarked internships.
     */
    fun loadBookmarkedInternships() {
        viewModelScope.launch {
            getBookmarkedInternshipsUseCase().onSuccess { list ->
                _bookmarkedInternships.value = list
            }.onFailure {
                _internshipState.value = InternshipState.Error("Error loading bookmarks: ${it.message}")
            }
        }
    }


//    init {
//        // Initialize the ViewModel by loading all internships
//        findAllBusinessOwnerIntership(101) // TODO: Cambiar por una variable
//        loadBookmarkedInternships()
//    }
}