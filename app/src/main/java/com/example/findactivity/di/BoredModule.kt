package com.example.findactivity.di

import androidx.room.Room
import com.example.findactivity.common.CoroutineContextProvider
import com.example.findactivity.data.dao.ActDatabase
import com.example.findactivity.domain.repository.ActRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val actModule = module {
    single { ActRepository(get(), get())  }
    single { CoroutineContextProvider() }
    single { Room.databaseBuilder(androidApplication(), ActDatabase::class.java, "bored-db")
        .build() }
    single { get<ActDatabase>().ActDAO()}
}