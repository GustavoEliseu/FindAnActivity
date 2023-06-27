package com.example.findactivity.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.findactivity.R
import com.example.findactivity.common.safeRun
import com.example.findactivity.data.model.ActEntity

class CardListAdapter() : RecyclerView.Adapter<BoredActivityViewHolder>() {

    private var activitiesList: MutableList<ActEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoredActivityViewHolder {
        return BoredActivityViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.act_card,
                parent,
                false
            )
        )
    }

    fun clearAndUpdateAll(list: MutableList<ActEntity>){
        activitiesList.clear()
        activitiesList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BoredActivityViewHolder, position: Int) {
        holder.bind(activitiesList[position])
    }

    override fun getItemCount() = activitiesList.size

    fun addItem(boredActivityEntity: ActEntity){
        activitiesList.add(boredActivityEntity)
        notifyItemInserted(activitiesList.lastIndex)
    }

    fun clear() {
        safeRun {
            activitiesList.clear()
            notifyDataSetChanged()
        }
    }
}