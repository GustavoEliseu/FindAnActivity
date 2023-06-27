package com.example.findactivity.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.findactivity.common.extensions.goBack
import com.example.findactivity.databinding.ActivityMainBinding

abstract class BaseActivity<VDB : ViewDataBinding>: AppCompatActivity(){

    lateinit var binding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayout());
        setContentView(binding.root)
        activityReady()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) finish() else goBack()
    }

    abstract fun getLayout(): Int

    abstract fun activityReady()
}