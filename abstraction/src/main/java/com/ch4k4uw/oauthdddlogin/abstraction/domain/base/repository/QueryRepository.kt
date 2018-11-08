package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.repository

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.entity.Entity
import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.repository.specification.ByIdRepositorySpecification
import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.repository.specification.RepositorySpecification

/**
 *
 */
interface QueryRepository<T, TId> where T: Entity<TId> {
    /**
     *
     */
    fun getById(spec: ByIdRepositorySpecification<T, TId>, success: (T) -> Unit, error: (Throwable) -> Unit)

    /**
     *
     */
    fun find(spec: RepositorySpecification<T, TId>, success: (List<T>) -> Unit, error: (Throwable) -> Unit)

}