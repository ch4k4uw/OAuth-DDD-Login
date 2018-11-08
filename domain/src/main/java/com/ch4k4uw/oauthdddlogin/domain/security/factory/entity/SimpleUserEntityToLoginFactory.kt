package com.ch4k4uw.oauthdddlogin.domain.security.factory.entity

import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity.UserEntity
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.entity.UserEntityToLoginFactory
import com.ch4k4uw.oauthdddlogin.domain.security.entity.SimpleUserEntity
import com.ch4k4uw.oauthdddlogin.domain.security.value.SimpleCompanyValue
import javax.inject.Inject

class SimpleUserEntityToLoginFactory @Inject constructor(): UserEntityToLoginFactory {
    override fun newUserEntity(email: String, pass: String): UserEntity
            = SimpleUserEntity(0, "", email, "", pass, SimpleCompanyValue.Empty)
}