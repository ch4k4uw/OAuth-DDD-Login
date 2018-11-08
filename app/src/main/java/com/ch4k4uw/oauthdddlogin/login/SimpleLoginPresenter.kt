package com.ch4k4uw.oauthdddlogin.login

import android.annotation.SuppressLint
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application.FindLoggedUserApplicationServer
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application.LoginApplicationService
import com.ch4k4uw.oauthdddlogin.abstraction.infrastructure.security.InvalidUserOrPasswordException
import com.ch4k4uw.oauthdddlogin.abstraction.infrastructure.security.NoLoggedUserException
import com.ch4k4uw.oauthdddlogin.abstraction.login.LoginPresenter
import com.ch4k4uw.oauthdddlogin.abstraction.login.LoginView
import com.ch4k4uw.oauthdddlogin.abstraction.util.AppPreferenceStorage
import com.ch4k4uw.oauthdddlogin.application.dto.LoggedUser
import io.reactivex.Observable
import javax.inject.Inject

class SimpleLoginPresenter @Inject constructor(private val loginService: LoginApplicationService<LoggedUser>,
                                               private val findLoggedUserService: FindLoggedUserApplicationServer<LoggedUser>,
                                               private val preferences: AppPreferenceStorage): LoginPresenter {
    private var mView: LoginView? = null
    private var mLoggedUser: LoggedUser? = null

    @SuppressLint("CheckResult")
    override fun loadLoginPreferences() {
        view?.enableLoginControls(false)
        val buffer = object {
            var login = ""
            var password = ""
            var remember = false
        }

        preferences
            .login
            .flatMap {
                buffer.login = it
                preferences.password
            }
            .flatMap {
                buffer.password = it
                preferences.rememberCredentials
            }
            .doOnError {
                it.printStackTrace()
                view?.enableLoginControls(true)
                view?.defineLoginPreferences(buffer.login, buffer.password, buffer.remember)
            }
            .subscribe {
                buffer.remember = it
                view?.enableLoginControls(true)
                view?.defineLoginPreferences(buffer.login, buffer.password, buffer.remember)
            }

    }

    override fun findLoggedUser() {
        view?.showIndeterminateProgress()
        view?.enableLoginControls(false)

        findLoggedUserService.find({
            view?.hideIndeterminateProgress()
            if(view?.showWelcomeView(it) == null) {
                mLoggedUser = it
            }
        }, {
            it.printStackTrace()
            view?.hideIndeterminateProgress()
            view?.enableLoginControls(true)
            if(it !is NoLoggedUserException) {
                view?.showGenericLoggedUserQueryErrorMessage()
            }
        })

    }

    override fun login(login: String, password: String, remember: Boolean) {
        if(login.isBlank()) {
            view?.showLoginObligationMessage()
            return
        } else if(password.isBlank()) {
            view?.showPasswordObligationMessage()
            return
        }

        view?.showIndeterminateProgress()
        view?.enableLoginControls(false)

        loginService.login(login, password, {
            view?.hideIndeterminateProgress()
            val showWelcomeMessage = {
                if (view?.showWelcomeView(it) == null) {
                    mLoggedUser = it
                }
            }

            if(remember) {
                preferences.login = Observable.just(login)
                preferences.password = Observable.just(password)
            } else {
                preferences.login = Observable.just("")
                preferences.password = Observable.just("")
            }

            preferences.rememberCredentials = Observable.just(remember)
            showWelcomeMessage()

        }, {
            it.printStackTrace()
            view?.hideIndeterminateProgress()
            view?.enableLoginControls(true)
            if(it is InvalidUserOrPasswordException) {
                view?.showInvalidLoginOrPasswordMessage()
            } else {
                view?.showGenericLoginErrorMessage()
            }
        })
    }

    override var view: LoginView?
        get() = mView
        set(value) {
            mView = value
            if(mView != null && mLoggedUser != null) {
                mView!!.showWelcomeView(mLoggedUser!!)
                mLoggedUser = null
            }
        }
}