package com.example.authflow.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authflow.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    repo: AuthRepository
) : ViewModel() {
    // tokenFlow emits String? (null = logged out)
    val isLoggedIn: StateFlow<Boolean> =
        repo.tokenFlow
            .map { !it.isNullOrBlank() }
            .stateIn(viewModelScope, SharingStarted.Eagerly, false)
}