package com.example.findactivity.common.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.showFragment(fragment: Fragment, @IdRes container: Int, addToBackStack: Boolean = false) {
    supportFragmentManager.beginTransaction().apply {
        if (addToBackStack) {
            addToBackStack(fragment.tag)
        }
    }
        .replace(container, fragment)
        .commit()
}

fun FragmentActivity.goBack() {
    supportFragmentManager.popBackStack()
}