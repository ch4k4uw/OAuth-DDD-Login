package com.ch4k4uw.oauthdddlogin.infrastructure.ioc.module.security

import com.ch4k4uw.oauthdddlogin.DesignScoped
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.entity.UserEntityBuilderFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.value.SecurityValueFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.repository.UserQueryRepository
import com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.factory.entity.SimpleUserEntityBuilderFactory
import com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.factory.value.SimpleSecurityValueFactory
import com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.repository.SimpleUserQueryRepository
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class SecurityDomainFactoryModule {
    @DesignScoped
    @Provides
    fun provideUserEntityBuilderFactory(companyFactory: SecurityValueFactory): UserEntityBuilderFactory
            = SimpleUserEntityBuilderFactory(companyFactory)

    @DesignScoped
    @Provides
    fun provideSecurityValueFactory(): SecurityValueFactory
            = SimpleSecurityValueFactory()

    @DesignScoped
    @Provides
    fun provideUserQueryRepository(userRepository: UserRepository,
                                   builderFactory: UserEntityBuilderFactory,
                                   valueFactory: SecurityValueFactory): UserQueryRepository
            = SimpleUserQueryRepository(userRepository, builderFactory, valueFactory)

}