package com.example.findactivity.networking.interceptor

import com.example.findactivity.R
import com.example.findactivity.common.HTTP_STATUS_CODE_OK
import com.example.findactivity.common.safeRunOnUiThread
import okhttp3.Interceptor
import okhttp3.Response

//todo - DEAL WITH STATUS OK AND ERRORS
class AuthInterceptor {
    fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        when {
            response.code() != HTTP_STATUS_CODE_OK -> {
                safeRunOnUiThread {
                }
            }
        }
        return response
    }
}