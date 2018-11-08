package com.ch4k4uw.oauthdddlogin.abstraction.domain.security.service

import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity.UserEntity

interface LogoutService {
    fun logout(user: UserEntity, success: () -> Unit, error: (Throwable) -> Unit)
}