package com.ch4k4uw.oauthdddlogin.ioc.module

import com.ch4k4uw.logintemplate.ioc.abstraction.ConstantsModule
import com.ch4k4uw.oauthdddlogin.PresentationScoped
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SimpleConstantsModule: ConstantsModule {
    @PresentationScoped
    @Provides
    @Named("app_animations")
    override fun provideAppAnimationsConst(): Boolean
            = true
}