package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.repository

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.entity.LongIdEntity

interface LongIdCommandRepository<T>: CommandRepository<T, Long> where T: LongIdEntity