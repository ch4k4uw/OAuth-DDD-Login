package com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.entity.LongIdEntity
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.service.LoginService
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.service.LogoutService
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.value.CompanyValue

interface UserEntity: LongIdEntity {
    val name: String
    val email: String
    val photo: String
    val password: String
    val company: CompanyValue

    fun login(service: LoginService, success: (UserEntity) -> Unit, error: (Throwable) -> Unit)
    fun logout(service: LogoutService, success: (UserEntity) -> Unit, error: (Throwable) -> Unit)

}