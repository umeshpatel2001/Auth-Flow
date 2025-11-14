package com.example.authflow.data.repository

import com.example.authflow.data.TokenStore
import com.example.authflow.data.remote.api.AuthApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val tokenStore: TokenStore
) {
    /**
     * Calls POST /api/login and saves token if success.
     * Returns Result<Unit>:
     *  - Success => Result.success(Unit)
     *  - Failure => Result.failure(Throwable)
     */
    suspend fun login(email: String, password: String): Result<Unit> {
        return runCatching {
            // Using the simple Map body we defined in Phase 2
            val body = mapOf("email" to email, "password" to password)
            val resp = api.login(body)         // may throw on HTTP error
            tokenStore.saveToken(resp.token)   // persist token
        }
    }

    // Expose token stream (null means logged out)
    val tokenFlow = tokenStore.tokenFlow

    // For logout later
    suspend fun logout() = tokenStore.clearToken()
}