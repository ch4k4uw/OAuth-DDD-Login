package com.ch4k4uw.oauthdddlogin.infrastructure.ioc

import android.content.Context
import com.ch4k4uw.oauthdddlogin.DesignScoped
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.entity.UserEntityBuilderFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.value.SecurityValueFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.repository.UserQueryRepository
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.service.LoginService
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.service.LogoutService
import com.ch4k4uw.oauthdddlogin.infrastructure.ioc.domain.ServiceDomainModule
import com.ch4k4uw.oauthdddlogin.infrastructure.ioc.module.security.SecurityDomainFactoryModule
import dagger.BindsInstance
import dagger.Component

@DesignScoped
@Component(modules = [InfraModule::class, SecurityDomainFactoryModule::class, ServiceDomainModule::class])
interface InfraComponent {
    val userRepository: UserQueryRepository
    val loginService: LoginService
    val logoutService: LogoutService
    val userEntityBuilder: UserEntityBuilderFactory
    val securityValueFactory: SecurityValueFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): InfraComponent

    }
}