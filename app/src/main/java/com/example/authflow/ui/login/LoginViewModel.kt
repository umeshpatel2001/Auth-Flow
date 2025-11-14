package com.example.authflow.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authflow.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {

    private val _ui = MutableStateFlow(LoginUiState())
    val ui: StateFlow<LoginUiState> = _ui

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _ui.value = LoginUiState(isLoading = true)
            val result = repo.login(email, password)
            _ui.value = result.fold(
                onSuccess = { LoginUiState(success = true) },
                onFailure = { LoginUiState(error = it.message ?: "Login failed") }
            )
        }
    }
}