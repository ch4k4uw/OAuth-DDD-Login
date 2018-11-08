package com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application

import java.io.Serializable

interface FindLoggedUserApplicationServer<T> where T: Serializable, T: Comparable<T>, T: Cloneable {
    fun find(success: (T) -> Unit, error: (Throwable) -> Unit)
}