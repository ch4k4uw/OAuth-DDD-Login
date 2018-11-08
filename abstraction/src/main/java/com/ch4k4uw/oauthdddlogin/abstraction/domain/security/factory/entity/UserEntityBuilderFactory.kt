package com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.entity

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.factory.LongIdEntityBuilderFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.builder.entity.UserEntityBuilder
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity.UserEntity

interface UserEntityBuilderFactory: LongIdEntityBuilderFactory<UserEntity, UserEntityBuilder>