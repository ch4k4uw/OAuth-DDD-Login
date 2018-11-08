package com.ch4k4uw.oauthdddlogin.domain.security.command

import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.command.LoggedUserQuery
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity.UserEntity
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.specification.SecuritySpecificationFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.repository.UserQueryRepository
import javax.inject.Inject

class SimpleLoggedUserQuery @Inject constructor(private val specFactory: SecuritySpecificationFactory, private val userRepository: UserQueryRepository):
    LoggedUserQuery {
    override fun exec(success: (UserEntity) -> Unit, error: (Throwable) -> Unit)
            = userRepository.getById(specFactory.newLoggedUserSpecification(), success, error)
}