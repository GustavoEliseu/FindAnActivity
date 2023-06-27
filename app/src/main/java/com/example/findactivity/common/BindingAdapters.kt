package com.example.findactivity.common

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.findactivity.common.extensions.getParentActivity

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->
            view.text = value ?: ""
        })
    }
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(
            parentActivity,
            Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("mutableTextColor")
fun setMutableTextColor(view: TextView, color: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && color != null) {
        color.observe(parentActivity, Observer { value ->
            view.setTextColor(ContextCompat.getColor(parentActivity, value))
        })
    }
}