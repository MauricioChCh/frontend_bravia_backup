package com.example.bravia.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bravia.presentation.ui.theme.AppThemeState
import com.example.bravia.presentation.ui.theme.ContrastMode
import com.example.bravia.presentation.ui.theme.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor() : ViewModel() {
    private val _themeState = MutableStateFlow(AppThemeState())
    val themeState: StateFlow<AppThemeState> = _themeState

    fun setThemeMode(themeMode: ThemeMode) {
        viewModelScope.launch {
            _themeState.emit(_themeState.value.copy(themeMode = themeMode))
        }
    }

    fun setContrastMode(contrastMode: ContrastMode) {
        viewModelScope.launch {
            _themeState.emit(_themeState.value.copy(contrastMode = contrastMode))
        }
    }


}