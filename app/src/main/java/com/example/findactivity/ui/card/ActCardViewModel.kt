package com.example.findactivity.ui.card

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.findactivity.base.BaseViewModel
import com.example.findactivity.database.BoredActEntity

class ActCardViewModel(): BaseViewModel() {

    val activityTitleMutableText = MutableLiveData<String>()
    val activityParticipantsMutableText = MutableLiveData<String>()
    val activityPriceMutableText = MutableLiveData<String>()
    val activityTypeMutableMutableText = MutableLiveData<String>()
    val activityLinkMutableMutableText = MutableLiveData<String>()
    val activityStatusMutableText = MutableLiveData<String>()
    val linkMutableVisibility = MutableLiveData<Int>()
    val activityListMutableVisibility = MutableLiveData<Int>()
    val activityStatusMutableTextColor = MutableLiveData<Int>()
    var currentAct: BoredActEntity? = null

    fun bind(boredAct: BoredActEntity, isFromRandomFragment:Boolean = false) {
        currentAct = boredAct
        activityTitleMutableText.value = boredAct.boredActivity
        activityParticipantsMutableText.value = boredAct.participants.toString()
        activityTypeMutableMutableText.value = boredAct.type
        activityPriceMutableText.value = boredAct.price.toString()
        if(boredAct.link.isEmpty()){
            linkMutableVisibility.value = View.GONE
        }else{
            linkMutableVisibility.value = View.VISIBLE
            activityLinkMutableMutableText.value = boredAct.link
        }
        activityListMutableVisibility.value = if(!isFromRandomFragment) View.GONE else View.VISIBLE

        if(!isFromRandomFragment){
            activityStatusMutableText.value = boredAct.status?.name
        }
    }

    fun deleteClick(){

    }
}