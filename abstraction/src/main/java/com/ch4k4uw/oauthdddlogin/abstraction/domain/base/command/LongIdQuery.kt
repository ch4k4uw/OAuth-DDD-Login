package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.command

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.entity.LongIdEntity

/**
 *
 */
interface LongIdQuery<T>: Query<T, Long> where T: LongIdEntity