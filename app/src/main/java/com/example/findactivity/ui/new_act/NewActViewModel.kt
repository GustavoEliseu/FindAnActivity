package com.example.findactivity.ui.new_act

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.findactivity.base.BaseViewModel

class NewActViewModel : BaseViewModel() {
    val contentVisibility = MutableLiveData<Int>()
    val addBtnMultableText = MutableLiveData<String>()


    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}