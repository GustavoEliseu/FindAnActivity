package com.example.findactivity.ui.new_act

import com.example.findactivity.R
import com.example.findactivity.base.BaseFragment
import com.example.findactivity.databinding.FragmentNewActBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewActFragment : BaseFragment<FragmentNewActBinding,NewActViewModel>() {
    private val mViewModel: NewActViewModel by viewModel()

    override fun getLayout(): Int = R.layout.fragment_new_act

    override fun fragmentReady() {
    }
}