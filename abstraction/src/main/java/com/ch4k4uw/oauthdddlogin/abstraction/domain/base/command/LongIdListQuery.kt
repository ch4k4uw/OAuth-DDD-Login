package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.command

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.entity.LongIdEntity

/**
 *
 */
interface LongIdListQuery<T>: ListQuery<T, Long> where T: LongIdEntity