package com.ch4k4uw.crosscutting.oauthdddlogin.ioc

import android.content.Context
import com.ch4k4uw.oauthdddlogin.DesignScoped
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application.FindLoggedUserApplicationServer
import com.ch4k4uw.oauthdddlogin.application.dto.LoggedUser
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application.LoginApplicationService
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application.LogoutApplicationService
import dagger.BindsInstance
import dagger.Component

@DesignScoped
@Component(modules = [AppDesignModule::class])
interface AppDesignComponent {
    val loginService: LoginApplicationService<LoggedUser>

    val findLoggedUserService: FindLoggedUserApplicationServer<LoggedUser>

    val logoutService: LogoutApplicationService

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppDesignComponent

    }
}