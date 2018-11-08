package com.ch4k4uw.oauthdddlogin.ioc.module

import com.ch4k4uw.oauthdddlogin.PresentationScoped
import com.ch4k4uw.oauthdddlogin.abstraction.util.AppFragmentManager
import com.ch4k4uw.oauthdddlogin.abstraction.util.AppPreferenceStorage
import com.ch4k4uw.oauthdddlogin.util.SimpleAppFragmentManager
import com.ch4k4uw.oauthdddlogin.util.SimpleAppPreferenceStorage
import dagger.Binds
import dagger.Module

@Module
interface UtilModule {
    @PresentationScoped
    @Binds
    fun bindAppFragmentManager(manager: SimpleAppFragmentManager): AppFragmentManager

    @PresentationScoped
    @Binds
    fun bindAppPreferenceStorage(preferenceStorage: SimpleAppPreferenceStorage): AppPreferenceStorage

}