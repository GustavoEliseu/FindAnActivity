package com.example.findactivity.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.findactivity.base.BaseViewModel
import com.example.findactivity.base.Loading
import com.example.findactivity.base.ViewState
import com.example.findactivity.base.ViewStateError
import com.example.findactivity.base.ViewStateSuccess
import com.example.findactivity.common.extensions.launch
import com.example.findactivity.data.model.ActEntity
import com.example.findactivity.data.model.onFailure
import com.example.findactivity.data.model.onSuccess
import com.example.findactivity.domain.usecase.GetActivityListDBUseCase
import com.example.findactivity.ui.list.adapter.CardListAdapter

class ActListViewModel(
    private val getAllListUseCase: GetActivityListDBUseCase
    ) : BaseViewModel() {

    val recyclerViewAdapter = CardListAdapter()

    fun updateRecycler(list: MutableList<ActEntity>){
        recyclerViewAdapter.clearAndUpdateAll(list)
    }

    fun addNewItem(boredActivityEntity: ActEntity){
        recyclerViewAdapter.addItem(boredActivityEntity)
    }

    fun getAllList() = executeUseCase {
        getAllListUseCase.invoke("")
            .onSuccess { _allListViewState.value = ViewStateSuccess(it) }
            .onFailure { _allListViewState.value = ViewStateError(it.throwable) }
    }


     fun executeUseCase(action: suspend () -> Unit) {
         _allListViewState.value = Loading()
        launch { action() }
    }

    protected val _allListViewState = MutableLiveData<ViewState<List<ActEntity>>>()
    val allListViewState: LiveData<ViewState<List<ActEntity>>>
        get() = _allListViewState
}