package com.ch4k4uw.oauthdddlogin.ioc

import android.content.Context
import com.ch4k4uw.oauthdddlogin.App
import com.ch4k4uw.oauthdddlogin.ioc.module.SimpleConstantsModule
import com.ch4k4uw.oauthdddlogin.ioc.module.UtilModule
import com.ch4k4uw.oauthdddlogin.FragmentScoped
import com.ch4k4uw.oauthdddlogin.login.SimpleLoginModule
import com.ch4k4uw.oauthdddlogin.login.SimpleLoginView
import com.ch4k4uw.oauthdddlogin.welcome.SimpleWelcomeModule
import com.ch4k4uw.oauthdddlogin.welcome.SimpleWelcomeView
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [UtilModule::class, SimpleConstantsModule::class])
interface AppModule {
    @Binds
    fun bindContext(app: App): Context

    @FragmentScoped
    @ContributesAndroidInjector(modules = [SimpleLoginModule::class])
    fun loginView(): SimpleLoginView

    @FragmentScoped
    @ContributesAndroidInjector(modules = [SimpleWelcomeModule::class])
    fun welcomeView(): SimpleWelcomeView

}