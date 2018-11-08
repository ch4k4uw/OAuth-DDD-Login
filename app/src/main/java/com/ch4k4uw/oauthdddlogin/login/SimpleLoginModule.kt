package com.ch4k4uw.oauthdddlogin.login

import com.ch4k4uw.oauthdddlogin.FragmentScoped
import com.ch4k4uw.oauthdddlogin.abstraction.login.LoginPresenter
import dagger.Binds
import dagger.Module

@Module
interface SimpleLoginModule {
    @FragmentScoped
    @Binds
    fun bindLoginPresenter(presenter: SimpleLoginPresenter): LoginPresenter
}