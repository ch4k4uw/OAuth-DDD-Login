package com.ch4k4uw.oauthdddlogin.abstraction.mvp

interface View {
    fun showIndeterminateProgress()
    fun hideIndeterminateProgress(force: Boolean = false)
}