package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.builder.entity

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.entity.Entity

interface EntityBuilder<T, TId> where T: Entity<TId> {
    fun build(): T
}