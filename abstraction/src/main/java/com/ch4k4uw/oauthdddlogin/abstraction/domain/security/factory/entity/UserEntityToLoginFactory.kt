package com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.entity

import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity.UserEntity

interface UserEntityToLoginFactory {
    fun newUserEntity(email: String, pass: String): UserEntity
}