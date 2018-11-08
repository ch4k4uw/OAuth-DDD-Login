package com.ch4k4uw.oauthdddlogin.abstraction.login

import com.ch4k4uw.oauthdddlogin.abstraction.mvp.Presenter

interface LoginPresenter: Presenter<LoginView> {
    fun loadLoginPreferences()
    fun findLoggedUser()
    fun login(login: String, password: String, remember: Boolean)
}