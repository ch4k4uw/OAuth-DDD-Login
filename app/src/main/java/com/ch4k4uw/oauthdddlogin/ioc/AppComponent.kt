package com.ch4k4uw.oauthdddlogin.ioc

import android.content.Context
import com.ch4k4uw.crosscutting.oauthdddlogin.ioc.AppDesignComponent
import com.ch4k4uw.oauthdddlogin.App
import com.ch4k4uw.oauthdddlogin.PresentationScoped
import com.ch4k4uw.oauthdddlogin.application.dto.LoggedUser
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application.LoginApplicationService
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application.LogoutApplicationService
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PresentationScoped
@Component(modules = [
    AppModule::class,
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class
], dependencies = [AppDesignComponent::class])
interface AppComponent: AndroidInjector<App> {
    val loginService: LoginApplicationService<LoggedUser>

    val logoutService: LogoutApplicationService

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<App>() {
        //abstract fun context(context: Context): AppComponent.Builder

        abstract fun design(design: AppDesignComponent): AppComponent.Builder

        abstract override fun build(): AppComponent
    }
}