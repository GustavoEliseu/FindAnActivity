package com.example.findactivity.ui.list

import android.widget.Toast
import com.example.findactivity.R
import com.example.findactivity.base.BaseFragment
import com.example.findactivity.base.ViewState
import com.example.findactivity.base.ViewStateError
import com.example.findactivity.base.ViewStateSuccess
import com.example.findactivity.common.GENERAL_DB_ERROR
import com.example.findactivity.common.extensions.subscribe
import com.example.findactivity.data.model.ActEntity
import com.example.findactivity.databinding.FragmentListActBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActListFragment : BaseFragment<FragmentListActBinding, ActListViewModel>() {

    private val viewModel: ActListViewModel by viewModel()

    override fun getLayout(): Int = R.layout.fragment_list_act

    override fun fragmentReady() {
        subscribeToData()
        viewModel.getAllList()
        mBinding.activitiesListRecyclerView.adapter = viewModel.recyclerViewAdapter
    }

    private fun handleListResult(viewState: ViewState<List<ActEntity>>) {
        when (viewState) {
            is ViewStateSuccess -> {
                viewModel.updateRecycler(viewState.data.toMutableList())
            }

            is ViewStateError -> {
                Toast.makeText(requireContext(), GENERAL_DB_ERROR, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun subscribeToData() {
        viewModel.allListViewState.subscribe(this, ::handleListResult)
    }
}