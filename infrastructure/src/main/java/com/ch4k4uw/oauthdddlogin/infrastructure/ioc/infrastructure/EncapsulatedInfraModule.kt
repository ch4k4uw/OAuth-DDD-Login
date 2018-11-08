package com.ch4k4uw.oauthdddlogin.infrastructure.ioc.infrastructure

import dagger.Module

@Module(includes = [EntityModule::class, ConfigModule::class])
interface EncapsulatedInfraModule