package com.ch4k4uw.oauthdddlogin.application.service

import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application.FindLoggedUserApplicationServer
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.command.LoggedUserQuery
import com.ch4k4uw.oauthdddlogin.application.dto.LoggedUser
import com.ch4k4uw.oauthdddlogin.application.dto.loggedUser
import javax.inject.Inject

class SimpleFindLoggedUserService @Inject constructor(private val userQuery: LoggedUserQuery): FindLoggedUserApplicationServer<LoggedUser> {
    override fun find(success: (LoggedUser) -> Unit, error: (Throwable) -> Unit)
            = userQuery.exec({
                success(
                    loggedUser {
                        name = it.name
                        this.email = it.email
                        photo = it.photo
                        this.password = it.password
                        company = com.ch4k4uw.oauthdddlogin.application.dto.company {
                            name = it.company.name
                            location = it.company.location
                            logo = it.company.logo
                        }
                    }
                )
            }, error)
}