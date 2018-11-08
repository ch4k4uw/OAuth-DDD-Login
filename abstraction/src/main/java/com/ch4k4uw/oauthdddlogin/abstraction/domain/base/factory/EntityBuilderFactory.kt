package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.factory

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.builder.entity.EntityBuilder
import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.entity.Entity

interface EntityBuilderFactory<T, TB, TId> where TB: EntityBuilder<T, TId>, T: Entity<TId> {
    fun newBuilder(id: TId): TB

}