package com.ch4k4uw.oauthdddlogin.abstraction.domain.security.application

import java.io.Serializable

interface LoginApplicationService<T> where T: Serializable, T: Comparable<T>, T: Cloneable {
    fun login(email: String, password: String, success: (T) -> Unit, error: (Throwable) -> Unit)
}