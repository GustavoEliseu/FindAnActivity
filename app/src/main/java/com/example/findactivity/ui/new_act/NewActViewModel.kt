package com.example.findactivity.ui.new_act

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.findactivity.base.BaseViewModel
import com.example.findactivity.base.NoIntentState
import com.example.findactivity.base.ViewState
import com.example.findactivity.base.ViewStateError
import com.example.findactivity.base.ViewStateSuccess
import com.example.findactivity.common.extensions.launch
import com.example.findactivity.data.model.ActEntity
import com.example.findactivity.data.model.onFailure
import com.example.findactivity.data.model.onSuccess
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

    protected val _alreadyExistsState = MutableLiveData<ViewState<Boolean>>()
    val alreadyExistsState: LiveData<ViewState<Boolean>>
        get() = _alreadyExistsState

    protected val _savedActState = MutableLiveData<ViewState<Boolean>>()
    val savedActState: LiveData<ViewState<Boolean>>
        get() = _savedActState

    protected val _newActViewState = MutableLiveData<ViewState<ActEntity>>()
    val newActViewState: LiveData<ViewState<ActEntity>>
        get() = _newActViewState

    fun bind(boredAct: ActEntity) {
        getAlreadyOnDB(boredAct)
    }

    fun clearSavedState(){
        _savedActState.value = NoIntentState()
    }

    fun changeAddBtn(addBtnText: String) {
        addBtnMultableText.value = addBtnText
    }

    fun getAlreadyOnDB(boredAct: ActEntity, isAdding: Boolean = false) = launch {
        getAlreadyOnDB.invoke(boredAct.key)
            .onSuccess {
                if (!isAdding) {
                    _alreadyExistsState.value = ViewStateSuccess(it)
                } else {
                    addActivityToDB(boredAct)
                }
            }.onFailure { _alreadyExistsState.value = ViewStateSuccess(false) }
    }

    fun getRandomActivity(lastKey: String? = "") = launch {
        getActivity.invoke(lastKey ?: "")
            .onSuccess { _newActViewState.value = ViewStateSuccess(it) }
            .onFailure { _newActViewState.value = ViewStateError(it.throwable) }
    }

    fun addActivityToDB(boredAct: ActEntity) = launch {
        saveOnDBUseCase.invoke(boredAct)
            .onSuccess { _savedActState.value = ViewStateSuccess(it) }
            .onFailure { _savedActState.value = ViewStateError(it.throwable) }
    }
}