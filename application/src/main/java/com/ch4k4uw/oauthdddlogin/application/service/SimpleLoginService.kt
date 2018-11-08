package com.ch4k4uw.oauthdddlogin.application.service

import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application.LoginApplicationService
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.entity.UserEntityToLoginFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.service.LoginService
import com.ch4k4uw.oauthdddlogin.application.dto.LoggedUser
import com.ch4k4uw.oauthdddlogin.application.dto.loggedUser
import javax.inject.Inject

class SimpleLoginService @Inject constructor(private val userFactory: UserEntityToLoginFactory, private val loginService: LoginService): LoginApplicationService<LoggedUser> {
    override fun login(email: String, password: String, success: (LoggedUser) -> Unit, error: (Throwable) -> Unit)
            = userFactory.newUserEntity(email, password).login(loginService, {
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
            }, {
                error(it)
            })
}