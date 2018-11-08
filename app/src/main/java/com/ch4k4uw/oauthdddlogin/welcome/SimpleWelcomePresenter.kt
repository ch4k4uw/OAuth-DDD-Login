package com.ch4k4uw.oauthdddlogin.welcome

import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application.FindLoggedUserApplicationServer
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application.LogoutApplicationService
import com.ch4k4uw.oauthdddlogin.abstraction.infrastructure.security.NoLoggedUserException
import com.ch4k4uw.oauthdddlogin.abstraction.welcome.WelcomePresenter
import com.ch4k4uw.oauthdddlogin.abstraction.welcome.WelcomeView
import com.ch4k4uw.oauthdddlogin.application.dto.LoggedUser
import javax.inject.Inject

class SimpleWelcomePresenter @Inject constructor(private val findLoggedUserService: FindLoggedUserApplicationServer<LoggedUser>,
                                                 private val logoutService: LogoutApplicationService): WelcomePresenter {
    private var mView: WelcomeView? = null
    private var mLoggedUser: LoggedUser? = null

    override fun findLoggedUser() {
        view?.showIndeterminateProgress()
        findLoggedUserService.find({
            view?.hideIndeterminateProgress()
            if(view?.showWelcomeMessage(it) == null) {
                mLoggedUser = it
            }
        }, {
            it.printStackTrace()
            view?.hideIndeterminateProgress()
            if(it !is NoLoggedUserException) {
                view?.showErrorOnFindLoggedUser()
            }
            view?.showLoginView()
        })

    }

    override fun logout() {
        view?.showIndeterminateProgress()
        view?.enableLogoutControls(false)
        logoutService.logout({
            view?.hideIndeterminateProgress()
            view?.showLoginView()
        }, {
            it.printStackTrace()
            view?.hideIndeterminateProgress()
            view?.showErrorMessageOnLogout()
            view?.enableLogoutControls(true)
        })
    }

    override var view: WelcomeView?
        get() = mView
        set(value) {
            mView = value
            if(mView != null && mLoggedUser != null) {
                mView?.showWelcomeMessage(mLoggedUser!!)
            }
        }
}