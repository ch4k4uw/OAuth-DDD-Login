package com.ch4k4uw.oauthdddlogin.abstraction.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface AppFragmentManager {
    fun beginTransaction(fragmentManager: FragmentManager): AppFragmentTransaction
    fun popBackStack(fragmentManager: FragmentManager)

    interface AppFragmentTransaction {
        fun animate(): AppFragmentTransaction
        fun replace(containerViewId: Int, fragment: Fragment, tag: String): AppFragmentTransaction
        fun addToBackStack(): AppFragmentTransaction
        fun commit(): Int
    }
}