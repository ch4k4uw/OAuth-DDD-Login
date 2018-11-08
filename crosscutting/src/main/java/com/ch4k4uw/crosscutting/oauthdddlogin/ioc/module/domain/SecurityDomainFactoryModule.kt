package com.ch4k4uw.crosscutting.oauthdddlogin.ioc.module.domain

import com.ch4k4uw.oauthdddlogin.DesignScoped
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.entity.UserEntityBuilderFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.entity.UserEntityToLoginFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.specification.SecuritySpecificationFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.value.SecurityValueFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.repository.UserQueryRepository
import com.ch4k4uw.oauthdddlogin.domain.security.factory.entity.SimpleUserEntityBuilderFactory
import com.ch4k4uw.oauthdddlogin.domain.security.factory.entity.SimpleUserEntityToLoginFactory
import com.ch4k4uw.oauthdddlogin.domain.security.factory.value.SimpleSecurityValueFactory
import com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.factory.SimpleSecuritySpecificationFactory
import com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.repository.SimpleUserQueryRepository
import dagger.Binds
import dagger.Module

@Module
interface SecurityDomainFactoryModule {
    @DesignScoped
    @Binds
    fun bindUserEntityBuilderFactory(factory: SimpleUserEntityBuilderFactory): UserEntityBuilderFactory

    @DesignScoped
    @Binds
    fun bindUserEntityToLoginFactory(factory: SimpleUserEntityToLoginFactory): UserEntityToLoginFactory

    @DesignScoped
    @Binds
    fun bindSecurityValueFactory(factory: SimpleSecurityValueFactory): SecurityValueFactory

    @DesignScoped
    @Binds
    fun bindUserQueryRepository(repository: SimpleUserQueryRepository): UserQueryRepository

    @DesignScoped
    @Binds
    fun bindSecuritySpecificationFactory(factory: SimpleSecuritySpecificationFactory): SecuritySpecificationFactory

}