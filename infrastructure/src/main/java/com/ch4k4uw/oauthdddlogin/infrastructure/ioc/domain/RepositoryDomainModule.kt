package com.ch4k4uw.oauthdddlogin.infrastructure.ioc.domain

import com.ch4k4uw.oauthdddlogin.InfraScoped
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.repository.UserQueryRepository
import com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.repository.SimpleUserQueryRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryDomainModule {
    @InfraScoped
    @Binds
    fun bindUserQueryRepository(repository: SimpleUserQueryRepository): UserQueryRepository

}