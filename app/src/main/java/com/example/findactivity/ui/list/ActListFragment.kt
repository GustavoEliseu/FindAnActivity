package com.example.findactivity.ui.list

import com.example.findactivity.R
import com.example.findactivity.base.BaseFragment
import com.example.findactivity.databinding.FragmentListActBinding
import com.example.findactivity.databinding.FragmentNewActBinding
import com.example.findactivity.ui.new_act.NewActViewModel

class ActListFragment : BaseFragment<FragmentListActBinding, ActListViewModel>() {

    override fun getLayout(): Int = R.layout.fragment_list_act

    override fun fragmentReady() {
    }
}