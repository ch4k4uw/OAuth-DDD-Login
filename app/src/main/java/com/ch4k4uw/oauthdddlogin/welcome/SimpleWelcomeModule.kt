package com.ch4k4uw.oauthdddlogin.welcome

import com.ch4k4uw.oauthdddlogin.FragmentScoped
import com.ch4k4uw.oauthdddlogin.abstraction.welcome.WelcomePresenter
import dagger.Binds
import dagger.Module

@Module
interface SimpleWelcomeModule {
    @FragmentScoped
    @Binds
    fun bindWelcomePresenter(presenter: SimpleWelcomePresenter): WelcomePresenter
}