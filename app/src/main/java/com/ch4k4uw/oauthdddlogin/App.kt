package com.ch4k4uw.oauthdddlogin

import com.ch4k4uw.crosscutting.oauthdddlogin.ioc.AppDesignComponent
import com.ch4k4uw.crosscutting.oauthdddlogin.ioc.DaggerAppDesignComponent
import com.ch4k4uw.oauthdddlogin.ioc.DaggerAppComponent
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App: DaggerApplication() {
    private var mAppDesignComponent: AppDesignComponent? = null
    private val appDesignComponent: AppDesignComponent get() {
        if(mAppDesignComponent == null) {
            mAppDesignComponent = DaggerAppDesignComponent.builder().context(this).build()
        }
        return mAppDesignComponent!!
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>
            = DaggerAppComponent.builder().design(appDesignComponent).create(this)

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }

}