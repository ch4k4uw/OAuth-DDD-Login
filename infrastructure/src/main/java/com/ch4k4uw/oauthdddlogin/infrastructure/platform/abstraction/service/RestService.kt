package com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.service

import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.platform.UserModel
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.rest.input.LoginInput
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.rest.result.LoginResult
import io.reactivex.Observable

interface RestService {
    fun login(credentials: LoginInput): Observable<UserModel>
    fun refreshToken(refreshToken: String): Observable<UserModel>
}