package com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.ws.service

import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.rest.input.LoginInput
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.ws.model.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {
    @POST("login")
    fun login(@Body credentials: LoginInput): Observable<LoginResponse>
}