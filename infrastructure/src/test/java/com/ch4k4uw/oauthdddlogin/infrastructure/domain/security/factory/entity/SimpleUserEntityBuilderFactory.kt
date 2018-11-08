package com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.factory.entity

import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.builder.entity.UserEntityBuilder
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.entity.UserEntityBuilderFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.value.SecurityValueFactory
import com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.builder.SimpleUserEntityBuilder
import javax.inject.Inject

class SimpleUserEntityBuilderFactory @Inject constructor(private val companyFactory: SecurityValueFactory): UserEntityBuilderFactory {
    override fun newBuilder(id: Long): UserEntityBuilder
            = SimpleUserEntityBuilder(id, companyFactory)
}