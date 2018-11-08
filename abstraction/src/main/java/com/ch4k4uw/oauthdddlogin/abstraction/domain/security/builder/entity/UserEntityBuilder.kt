package com.ch4k4uw.oauthdddlogin.abstraction.domain.security.builder.entity

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.builder.entity.LongIdEntityBuilder
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity.UserEntity
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.value.CompanyValue

interface UserEntityBuilder: LongIdEntityBuilder<UserEntity> {
    fun withName(name: String): UserEntityBuilder
    fun withEmail(email: String): UserEntityBuilder
    fun withPhoto(photo: String): UserEntityBuilder
    fun withPassword(password: String): UserEntityBuilder
    fun withCompany(company: CompanyValue): UserEntityBuilder

}