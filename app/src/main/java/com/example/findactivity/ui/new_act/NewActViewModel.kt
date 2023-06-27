package com.example.findactivity.ui.new_act

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.findactivity.base.BaseViewModel
import com.example.findactivity.domain.usecase.GetAlreadyOnDBUseCase
import com.example.findactivity.domain.usecase.GetRandomActivityUseCase
import com.example.findactivity.domain.usecase.SaveOnDBUseCase

class NewActViewModel(
    private val getActivity: GetRandomActivityUseCase,
    private val getAlreadyOnDB: GetAlreadyOnDBUseCase,
    private val saveOnDBUseCase: SaveOnDBUseCase
) : BaseViewModel() {
    val contentVisibility = MutableLiveData<Int>()
    val addBtnMultableText = MutableLiveData<String>()


    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}