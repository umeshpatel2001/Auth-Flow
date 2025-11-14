package com.example.authflow.ui.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.authflow.ui.session.SessionViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    toLogin: () -> Unit,
    toHome: () -> Unit,
    vm: SessionViewModel = hiltViewModel()
) {
    // Observe the login flag once, then navigate
    LaunchedEffect(Unit) {
        vm.isLoggedIn.collectLatest { loggedIn ->
            if (loggedIn) toHome() else toLogin()
        }
    }

    // Simple centered loader while we decide
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}