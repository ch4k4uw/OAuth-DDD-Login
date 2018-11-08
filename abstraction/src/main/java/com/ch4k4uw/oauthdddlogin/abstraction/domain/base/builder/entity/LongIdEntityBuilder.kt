package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.builder.entity

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.entity.LongIdEntity

interface LongIdEntityBuilder<T>: EntityBuilder<T, Long> where T: LongIdEntity