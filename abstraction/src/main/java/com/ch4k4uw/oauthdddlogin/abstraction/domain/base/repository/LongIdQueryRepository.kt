package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.repository

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.entity.LongIdEntity

interface LongIdQueryRepository<T>: QueryRepository<T, Long> where T: LongIdEntity