package com.example.findactivity.ui.new_act

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.setFragmentResult
import com.example.findactivity.R
import com.example.findactivity.base.BaseFragment
import com.example.findactivity.base.Loading
import com.example.findactivity.base.NoInternetState
import com.example.findactivity.base.ViewState
import com.example.findactivity.base.ViewStateError
import com.example.findactivity.base.ViewStateSuccess
import com.example.findactivity.common.NEW_ACTIVITY_ADDED_KEY
import com.example.findactivity.common.NEW_ACTIVITY_ADDED_REQUEST
import com.example.findactivity.common.extensions.onClick
import com.example.findactivity.common.extensions.subscribe
import com.example.findactivity.data.model.ActEntity
import com.example.findactivity.databinding.FragmentNewActBinding
import com.example.findactivity.ui.card.ActCardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewActFragment : BaseFragment<FragmentNewActBinding,NewActViewModel>() {
    private val mViewModel: NewActViewModel by viewModel()

    private val viewModel: NewActViewModel by viewModel()
    private val cardViewModel: ActCardViewModel by viewModel()
    var lastKey = ""
    var tempActivity: ActEntity? = null
    var currentExists: Boolean = false
    var firstTime: Boolean = true

    override fun getLayout(): Int = R.layout.fragment_new_act

    override fun fragmentReady() {
        subscribeToData();
        mBinding.setVariable(BR.viewModel, viewModel)
        mBinding.setVariable(BR.cardViewModel, cardViewModel)
        if(firstTime)
            viewModel.getRandomActivity(lastKey)
        firstTime = false
        mBinding.getNewActBtn.onClick {
            mBinding.getNewActBtn.isEnabled = false
            currentExists = false
            viewModel.getRandomActivity(lastKey)
        }
        mBinding.addCurrentActBtn.onClick {
            cardViewModel.currentAct?.let { act ->
                mBinding.addCurrentActBtn.isEnabled = false
                viewModel.addActivityToDB(act)
                tempActivity = act;
            }
        }
    }

    private fun subscribeToData() {
        viewModel.newActViewState.subscribe(this, ::handleViewState)
        viewModel.alreadyExistsState.subscribe(this, ::handleAlreadyExistsViewState)
        viewModel.savedActState.subscribe(this, ::handleSavedActState)
    }

    private fun handleSavedActState(viewState: ViewState<Boolean>) {
        when (viewState) {
            is ViewStateSuccess -> {
                onSuccessSavedActivity(viewState.data)
                viewModel.clearSavedState()
            }
            is ViewStateError -> Toast.makeText(
                requireContext(),
                getString(R.string.save_not_possible),
                Toast.LENGTH_SHORT
            ).show()
            is NoInternetState  ->{}
        }
    }

    //Gets new random value, no time to change viewState variable on BaseViewModel
    private fun handleViewState(viewState: ViewState<ActEntity>) {
        if (viewState !is Loading) mBinding.getNewActBtn.isEnabled = true
        when (viewState) {
            is ViewStateSuccess -> {
                onSuccessStartBinding(viewState.data)
            }
            is ViewStateError -> Toast.makeText(requireContext(), viewState.error.message, Toast.LENGTH_SHORT)
                .show()
            is NoInternetState -> Toast.makeText(
                requireContext(),
                "Connection Error",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    private fun handleAlreadyExistsViewState(viewState: ViewState<Boolean>) {
        if(viewState !is Loading && viewState !is ViewStateSuccess) mBinding.addCurrentActBtn.isEnabled = !currentExists
        when (viewState) {
            is ViewStateSuccess -> onSuccessAlreadyExists(viewState.data)
            is ViewStateError -> Toast.makeText(
                requireContext(),
                "Error checking if activity already on list",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun onSuccessStartBinding(boredAct: ActEntity) {
        lastKey = boredAct.key
        cardViewModel.bind(boredAct)
        viewModel.bind(boredAct)
    }

    private fun onSuccessAlreadyExists(exists: Boolean) {
        viewModel.changeAddBtn(getString(if (exists) R.string.already_exists else R.string.add))
        mBinding.addCurrentActBtn.isEnabled = !exists
        currentExists = exists
    }

    private fun onSuccessSavedActivity(success: Boolean) {
        if (success) {
            Toast.makeText(
                requireContext(),
                getString(R.string.activity_added),
                Toast.LENGTH_SHORT
            ).show()
            tempActivity?.let {
                setFragmentResult(NEW_ACTIVITY_ADDED_REQUEST, bundleOf(NEW_ACTIVITY_ADDED_KEY to it))
                tempActivity = null
            }

        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.save_not_possible),
                Toast.LENGTH_SHORT
            ).show()
        }
        onSuccessAlreadyExists(success)
    }
}