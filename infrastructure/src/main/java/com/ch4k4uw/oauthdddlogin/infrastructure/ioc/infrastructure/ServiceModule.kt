package com.ch4k4uw.oauthdddlogin.infrastructure.ioc.infrastructure

import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.service.RestService
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.ws.service.SimpleRestService
import dagger.Binds
import dagger.Module

@Module
interface ServiceModule {
    @Binds
    fun bindRestService(service: SimpleRestService): RestService
}