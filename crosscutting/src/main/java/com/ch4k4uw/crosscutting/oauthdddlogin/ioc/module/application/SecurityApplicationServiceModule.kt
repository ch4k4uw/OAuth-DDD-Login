package com.ch4k4uw.crosscutting.oauthdddlogin.ioc.module.application

import com.ch4k4uw.oauthdddlogin.DesignScoped
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application.FindLoggedUserApplicationServer
import com.ch4k4uw.oauthdddlogin.application.dto.LoggedUser
import com.ch4k4uw.oauthdddlogin.application.service.SimpleLoginService
import com.ch4k4uw.oauthdddlogin.application.service.SimpleLogoutService
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application.LoginApplicationService
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application.LogoutApplicationService
import com.ch4k4uw.oauthdddlogin.application.service.SimpleFindLoggedUserService
import dagger.Binds
import dagger.Module

@Module
interface SecurityApplicationServiceModule {
    @DesignScoped
    @Binds
    fun bindLoginService(service: SimpleLoginService): LoginApplicationService<LoggedUser>

    @DesignScoped
    @Binds
    fun bindFindLoggedUserService(service: SimpleFindLoggedUserService): FindLoggedUserApplicationServer<LoggedUser>

    @DesignScoped
    @Binds
    fun bindLogoutService(service: SimpleLogoutService): LogoutApplicationService

}