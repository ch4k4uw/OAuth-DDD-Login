package com.ch4k4uw.oauthdddlogin.abstraction.domain.security.service

import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity.UserEntity

interface LoginService {
    fun login(user: UserEntity, success: (UserEntity) -> Unit, error: (Throwable) -> Unit)
}