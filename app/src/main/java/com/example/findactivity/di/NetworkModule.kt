package com.example.findactivity.di

import com.example.findactivity.common.BASE_URL
import com.example.findactivity.networking.ActApi
import com.example.findactivity.networking.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//Decided there was no need for an interceptor in this case, since it's a free api
val networkModule = module {
    single { GsonConverterFactory.create() as Converter.Factory }
    factory { AuthInterceptor() }
    factory { provideOkHttpClient() }
    factory { provideBoredApi(get()) }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(get())
            .build()
    }
}


fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().build()
}

fun provideBoredApi(retrofit: Retrofit): ActApi = retrofit.create(ActApi::class.java)
