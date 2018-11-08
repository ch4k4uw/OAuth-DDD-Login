package com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.service

import android.annotation.SuppressLint
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity.UserEntity
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.service.LogoutService
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.repository.UserRepository
import javax.inject.Inject

class SimpleLogoutService @Inject constructor(private val userRepository: UserRepository): LogoutService {
    @SuppressLint("CheckResult")
    override fun logout(user: UserEntity, success: () -> Unit, error: (Throwable) -> Unit) {
        userRepository
                .deleteLoggedUser()
                .subscribe({
                    success()
                }, error)
    }
}