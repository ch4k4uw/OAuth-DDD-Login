package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.repository.specification

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.entity.LongIdEntity

interface ByLongIdRepositorySpecification<T>: ByIdRepositorySpecification<T, Long> where T: LongIdEntity