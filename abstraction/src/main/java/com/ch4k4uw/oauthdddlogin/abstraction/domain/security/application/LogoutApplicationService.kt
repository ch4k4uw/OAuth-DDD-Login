package com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application

interface LogoutApplicationService {
    fun logout(success: () -> Unit, error: (Throwable) -> Unit)
}