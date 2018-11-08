package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.repository

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.entity.Entity
import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.repository.specification.ByIdRepositorySpecification

/**
 *
 */
interface CommandRepository<T, TId> where T: Entity<TId> {
    /**
     *
     */
    fun insert(entity: T, success: (TId) -> Unit, error: (Throwable) -> Unit)

    /**
     *
     */
    fun update(entity: T, spec: ByIdRepositorySpecification<T, TId>, success: (T) -> Unit, error: (Throwable) -> Unit)

    /**
     *
     */
    fun delete(spec: ByIdRepositorySpecification<T, TId>, success: (Boolean) -> Unit, error: (Throwable) -> Unit)

}