package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.builder.value

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.value.Value

interface ValueBuilder<T> where T: Value {
    fun build(): T
}