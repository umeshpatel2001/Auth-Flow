package com.example.authflow.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenProvider @Inject constructor(
    private val tokenStore: TokenStore
) {
    @Volatile
    private var cachedToken: String? = null

    private val scope = CoroutineScope(Dispatchers.IO + Job()).apply {
        // collect tokenFlow and keep latest value cached in memory
        launch {
            tokenStore.tokenFlow.collectLatest { token ->
                cachedToken = token
            }
        }
    }

    fun getToken(): String? = cachedToken
}