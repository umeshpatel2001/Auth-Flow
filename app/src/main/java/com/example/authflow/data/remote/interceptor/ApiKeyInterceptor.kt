package com.example.authflow.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiKeyInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            // Add demo header — reqres doesn’t actually verify this value,
            // it just checks the presence of a header.
            .addHeader("x-api-key", "reqres-free-v1")
            .build()
        return chain.proceed(request)
    }
}