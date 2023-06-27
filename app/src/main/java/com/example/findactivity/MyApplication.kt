package com.example.findactivity

import android.app.Application
import com.example.findactivity.di.actModule
import com.example.findactivity.di.networkModule
import com.example.findactivity.domain.repository.actRepModule
import com.example.imbored.di.module.presentationModule
import com.example.imbored.di.module.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@MyApplication)
            modules(modules)
        }
    }
}

val modules =
    listOf(presentationModule, actModule,actRepModule, networkModule, useCaseModule)