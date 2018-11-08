package com.ch4k4uw.oauthdddlogin.abstraction.login

import com.ch4k4uw.oauthdddlogin.abstraction.mvp.View
import com.ch4k4uw.oauthdddlogin.application.dto.LoggedUser

interface LoginView: View {
    fun showWelcomeView(loggedUser: LoggedUser)
    fun showLoginObligationMessage()
    fun showPasswordObligationMessage()
    fun enableLoginControls(enable: Boolean)
    fun showInvalidLoginOrPasswordMessage()
    fun showGenericLoginErrorMessage()
    fun showGenericLoggedUserQueryErrorMessage()
    fun defineLoginPreferences(login: String, password: String, remember: Boolean)

}