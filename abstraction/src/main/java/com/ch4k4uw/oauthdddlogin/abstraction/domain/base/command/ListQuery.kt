package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.command

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.entity.Entity

/**
 *
 */
interface ListQuery<T, TId> where T: Entity<TId> {
    /**
     *
     */
    fun exec(success: (List<T>) -> Unit, error: (Throwable) -> Unit)
}