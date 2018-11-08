package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.factory

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.builder.entity.EntityBuilder
import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.entity.LongIdEntity

interface LongIdEntityBuilderFactory<T, TB>: EntityBuilderFactory<T, TB, Long> where T: LongIdEntity, TB: EntityBuilder<T, Long>