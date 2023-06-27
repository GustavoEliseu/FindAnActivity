package com.example.findactivity.ui.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.findactivity.data.model.ActEntity
import com.example.findactivity.databinding.ActCardBinding
import com.example.findactivity.ui.card.ActCardViewModel

class BoredActivityViewHolder(
    private val binding: ActCardBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val viewModel = ActCardViewModel()

    fun bind(boredActivityEntity: ActEntity) {
        binding.viewModel = viewModel
        viewModel.bind(boredActivityEntity)
    }
}