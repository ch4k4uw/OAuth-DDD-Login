package com.ch4k4uw.oauthdddlogin.infrastructure.ioc.infrastructure

import android.content.Context
import com.ch4k4uw.oauthdddlogin.infrastructure.R
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.config.InfraConfig
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.repository.UserRepository
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.ws.interceptor.AuthInterceptor
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.ws.interceptor.SimpleAuthInterceptor
import dagger.Module
import dagger.Provides

@Module
class ConfigModule {
    @Provides
    fun provideInterceptor(repository: UserRepository): AuthInterceptor {
        return SimpleAuthInterceptor(repository)
    }

    @Provides
    fun provideInfraConfig(context: Context): InfraConfig = object : InfraConfig {
        override val oAuthGrantType: String
            get()
                    = context.getString(R.string.oauth_grant_type)
        override val oAuthRefreshGrantType: String
            get()
                    = context.getString(R.string.oauth_refresh_grant_type)
        override val oAuthClientId: String
            get()
                    = context.getString(R.string.oauth_client_id)
        override val oAuthClientSecret: String
            get()
                    = context.getString(R.string.oauth_client_secret)
        override val oAuthScope: String
            get()
                    = context.getString(R.string.oauth_scope)
        override val gsonDateFormat: String
            get()
                    = context.getString(R.string.gson_date_format)
        override val baseRestUrl: String
            get()
                    = context.getString(R.string.server_host)
    }

}