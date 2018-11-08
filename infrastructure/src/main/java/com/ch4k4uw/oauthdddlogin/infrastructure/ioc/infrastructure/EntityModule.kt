package com.ch4k4uw.oauthdddlogin.infrastructure.ioc.infrastructure

import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.platform.UserModel
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.ws.model.SimpleUserModel
import dagger.Module
import dagger.Provides

@Module
class EntityModule {
    @Provides
    fun provideUserModel(): UserModel = SimpleUserModel()
}