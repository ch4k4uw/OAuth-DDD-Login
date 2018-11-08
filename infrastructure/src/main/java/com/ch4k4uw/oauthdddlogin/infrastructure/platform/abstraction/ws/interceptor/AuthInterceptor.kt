package com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.ws.interceptor

import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.platform.UserModel
import okhttp3.Interceptor

interface AuthInterceptor: Interceptor {
    var refreshTokenHandler: ((String, (Throwable) -> Unit) -> UserModel)?
}