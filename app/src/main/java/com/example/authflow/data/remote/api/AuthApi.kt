package com.example.authflow.data.remote.api

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    // POST https://reqres.in/api/login
    @POST("api/login")
    suspend fun login(@Body body: Map<String, String>): TokenDto
}

// Response DTO (just the token field we care about)
data class TokenDto(val token: String)