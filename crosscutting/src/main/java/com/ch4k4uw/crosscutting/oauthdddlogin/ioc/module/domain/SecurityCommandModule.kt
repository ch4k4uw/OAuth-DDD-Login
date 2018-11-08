package com.ch4k4uw.crosscutting.oauthdddlogin.ioc.module.domain

import com.ch4k4uw.oauthdddlogin.DesignScoped
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.command.LoggedUserQuery
import com.ch4k4uw.oauthdddlogin.domain.security.command.SimpleLoggedUserQuery
import dagger.Binds
import dagger.Module

@Module
interface SecurityCommandModule {
    @DesignScoped
    @Binds
    fun bindLoggedUserQuery(query: SimpleLoggedUserQuery): LoggedUserQuery
}