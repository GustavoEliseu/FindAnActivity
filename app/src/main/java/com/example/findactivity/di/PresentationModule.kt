package com.example.imbored.di.module


import com.example.findactivity.ui.card.ActCardViewModel
import com.example.findactivity.ui.list.ActListViewModel
import com.example.findactivity.ui.new_act.NewActViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { NewActViewModel(get(), get(), get()) }
    viewModel{ ActCardViewModel() }
    viewModel { ActListViewModel(get()) }
}