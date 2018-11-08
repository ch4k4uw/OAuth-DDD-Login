package com.ch4k4uw.oauthdddlogin.abstraction.welcome

import com.ch4k4uw.oauthdddlogin.abstraction.mvp.View
import com.ch4k4uw.oauthdddlogin.application.dto.LoggedUser

interface WelcomeView: View {
    fun showWelcomeMessage(user: LoggedUser)
    fun showLoginView()
    fun showErrorMessageOnLogout()
    fun showErrorOnFindLoggedUser()
    fun enableLogoutControls(enable: Boolean)
}