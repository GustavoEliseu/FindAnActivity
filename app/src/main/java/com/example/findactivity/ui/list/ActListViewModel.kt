package com.example.findactivity.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findactivity.base.BaseViewModel
import com.example.findactivity.domain.usecase.GetActivityListDBUseCase

class ActListViewModel(
    private val getAllListUseCase: GetActivityListDBUseCase
    ) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}