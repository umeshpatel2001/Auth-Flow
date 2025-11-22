package com.example.authflow.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment

@Composable
fun HomeScreen() {
    Text(
        text = "Welcome! 🎉 You’re logged in to app",
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
    )
}