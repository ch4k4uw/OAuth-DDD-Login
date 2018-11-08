package com.ch4k4uw.oauthdddlogin.abstraction.domain.security.command

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.command.LongIdQuery
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity.UserEntity

interface LoggedUserQuery: LongIdQuery<UserEntity>