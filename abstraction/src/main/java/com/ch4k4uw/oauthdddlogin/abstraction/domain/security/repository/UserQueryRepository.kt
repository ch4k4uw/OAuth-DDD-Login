package com.ch4k4uw.oauthdddlogin.abstraction.domain.security.repository

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.repository.LongIdQueryRepository
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity.UserEntity

interface UserQueryRepository: LongIdQueryRepository<UserEntity>