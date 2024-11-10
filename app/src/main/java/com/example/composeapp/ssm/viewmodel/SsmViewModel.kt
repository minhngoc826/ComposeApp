package com.example.composeapp.ssm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.composeapp.ssm.ui.uistate.SsmUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SsmViewModel : ViewModel() {

    private val _ssmUiState = MutableStateFlow(SsmUiState(title = "SSM", subtitle = "Main"))
    val ssmUiState: StateFlow<SsmUiState> = _ssmUiState.asStateFlow()


    fun updateTitle(title: String) {
        _ssmUiState.update { it.copy(title = title) }
    }

    fun updateSubtitle(subtitle: String) {
        _ssmUiState.update { it.copy(subtitle = subtitle) }
    }
}