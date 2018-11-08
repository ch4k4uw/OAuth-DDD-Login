package com.ch4k4uw.oauthdddlogin.abstraction.welcome

import com.ch4k4uw.oauthdddlogin.abstraction.mvp.Presenter

interface WelcomePresenter: Presenter<WelcomeView> {
    fun findLoggedUser()
    fun logout()
}