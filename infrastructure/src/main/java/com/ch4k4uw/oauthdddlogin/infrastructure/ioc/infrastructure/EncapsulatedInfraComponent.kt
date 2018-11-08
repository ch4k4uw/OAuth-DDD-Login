package com.ch4k4uw.oauthdddlogin.infrastructure.ioc.infrastructure

import android.content.Context
import com.ch4k4uw.oauthdddlogin.InfraScoped
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.config.InfraConfig
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.platform.UserModel
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.repository.UserRepository
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.service.RestService
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.ws.interceptor.AuthInterceptor
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.repository.SimpleUserRepository
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.ws.service.SimpleRestService
import dagger.Subcomponent
import javax.inject.Provider

@Subcomponent(modules = [EncapsulatedInfraModule::class])
abstract class EncapsulatedInfraComponent {
    protected abstract val context: Context
    protected abstract val userModelProvider: Provider<UserModel>
    protected abstract val authInterceptor: AuthInterceptor
    protected abstract val infraConfig: InfraConfig

    @InfraScoped
    fun userRepository(): UserRepository
            = SimpleUserRepository(context, userModelProvider)

    @InfraScoped
    fun restService(userRepository: UserRepository): RestService
            = SimpleRestService(infraConfig, userRepository, userModelProvider, authInterceptor)

    @Subcomponent.Builder
    interface Builder {
        fun build(): EncapsulatedInfraComponent
    }
}