package com.ch4k4uw.oauthdddlogin.infrastructure.ioc

import com.ch4k4uw.oauthdddlogin.DesignScoped
import com.ch4k4uw.oauthdddlogin.infrastructure.ioc.infrastructure.EncapsulatedInfraComponent
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module(subcomponents = [EncapsulatedInfraComponent::class])
class InfraModule {
    @Provides
    @DesignScoped
    fun provideRepositoryComponent(builder: EncapsulatedInfraComponent.Builder)
            = builder.build()

    @Provides
    @DesignScoped
    fun provideUserRepository(component: EncapsulatedInfraComponent)
            = component.userRepository()

    @Provides
    @DesignScoped
    fun provideRestService(component: EncapsulatedInfraComponent, userRepository: UserRepository)
            = component.restService(userRepository)

}