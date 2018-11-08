package com.ch4k4uw.oauthdddlogin.application.service

import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application.LogoutApplicationService
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.command.LoggedUserQuery
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.service.LogoutService
import javax.inject.Inject

class SimpleLogoutService @Inject constructor(private val userQuery: LoggedUserQuery, private val service: LogoutService) : LogoutApplicationService {
    override fun logout(success: () -> Unit, error: (Throwable) -> Unit)
            = userQuery.exec({ loggedUser ->
                loggedUser.logout(service, { success() }, error)
            }, error)
}