package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.repository.specification

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.entity.LongIdEntity

interface LongIdRepositorySpecification<T>: RepositorySpecification<T, Long> where T: LongIdEntity