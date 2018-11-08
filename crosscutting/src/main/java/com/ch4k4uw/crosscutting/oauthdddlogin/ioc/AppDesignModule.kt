package com.ch4k4uw.crosscutting.oauthdddlogin.ioc

import com.ch4k4uw.crosscutting.oauthdddlogin.ioc.module.application.SecurityApplicationServiceModule
import com.ch4k4uw.crosscutting.oauthdddlogin.ioc.module.domain.SecurityCommandModule
import com.ch4k4uw.crosscutting.oauthdddlogin.ioc.module.domain.SecurityDomainFactoryModule
import com.ch4k4uw.oauthdddlogin.infrastructure.ioc.InfraModule
import com.ch4k4uw.oauthdddlogin.infrastructure.ioc.domain.ServiceDomainModule
import dagger.Module

@Module(includes = [
    SecurityApplicationServiceModule::class,
    SecurityCommandModule::class,
    SecurityDomainFactoryModule::class,
    ServiceDomainModule::class,
    InfraModule::class
])
interface AppDesignModule