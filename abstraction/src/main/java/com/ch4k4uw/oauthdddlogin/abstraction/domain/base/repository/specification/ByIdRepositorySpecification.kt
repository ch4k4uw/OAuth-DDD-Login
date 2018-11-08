package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.repository.specification

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.entity.Entity

interface ByIdRepositorySpecification<T, TId> where T: Entity<TId>