package com.ch4k4uw.oauthdddlogin.infrastructure.ioc.domain

import com.ch4k4uw.oauthdddlogin.DesignScoped
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.service.LoginService
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.service.LogoutService
import com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.service.SimpleLoginService
import com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.service.SimpleLogoutService
import dagger.Binds
import dagger.Module

@Module
interface ServiceDomainModule {
    @DesignScoped
    @Binds
    fun bindLoginService(service: SimpleLoginService): LoginService

    @DesignScoped
    @Binds
    fun bindLogoutService(service: SimpleLogoutService): LogoutService

}