package com.example.imbored.di.module

import com.example.findactivity.domain.usecase.GetActivityListDBUseCase
import com.example.findactivity.domain.usecase.GetActivityListDBUseCaseImpl
import com.example.findactivity.domain.usecase.GetAlreadyOnDBUseCase
import com.example.findactivity.domain.usecase.GetAlreadyOnDBUseCaseImpl
import com.example.findactivity.domain.usecase.GetRandomActivityUseCase
import com.example.findactivity.domain.usecase.GetRandomActivityUseCaseImpl
import com.example.findactivity.domain.usecase.SaveOnDBUseCase
import com.example.findactivity.domain.usecase.SaveOnDBUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetRandomActivityUseCase> { GetRandomActivityUseCaseImpl(get()) }
    factory<GetAlreadyOnDBUseCase> { GetAlreadyOnDBUseCaseImpl(get()) }
    factory<SaveOnDBUseCase> { SaveOnDBUseCaseImpl(get()) }
    factory<GetActivityListDBUseCase> { GetActivityListDBUseCaseImpl(get()) }
}
