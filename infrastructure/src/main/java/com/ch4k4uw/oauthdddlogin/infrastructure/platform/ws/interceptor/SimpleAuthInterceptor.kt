package com.ch4k4uw.oauthdddlogin.infrastructure.platform.ws.interceptor

import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.exception.TokenExpiredException
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.platform.UserModel
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.repository.UserRepository
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.ws.interceptor.AuthInterceptor
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject

class SimpleAuthInterceptor @Inject constructor(private val userRepository: UserRepository): AuthInterceptor {
    private var mRefreshTokenHandler: ((String, (Throwable) -> Unit) -> UserModel)? = null
    override var refreshTokenHandler: ((String, (Throwable) -> Unit) -> UserModel)?
        get() = mRefreshTokenHandler
        set(value) {
            mRefreshTokenHandler = value
        }

    private fun UserModel.isAccessTokenExpired(): Boolean
            = this.accessTokenExpiration == 0L || this.accessTokenExpiration < System.currentTimeMillis()

    override fun intercept(chain: Interceptor.Chain): Response {
        var user: UserModel?
        var lastError: Throwable? = null

        user = userRepository
                .loggedUser
                .doOnError {
                    lastError = it
                }
                .blockingSingle()

        if (lastError == null) {
            if (user.isAccessTokenExpired()) {
                if(refreshTokenHandler != null) {
                    /*user = restService.get()
                            .refreshToken(user.refreshToken)
                            .flatMap { userRepository.loggedUser }
                            .doOnError {
                                lastError = it
                            }
                            .blockingSingle()*/
                    user = refreshTokenHandler!!(user.refreshToken){
                        lastError = it
                    }

                } else {
                    lastError = TokenExpiredException()
                }

            }
        }

        if(lastError != null) {
            throw lastError!!
        }

        val original = chain.request()
        return chain.proceed(
                original
                        .newBuilder()
                        .header("language", "${Locale.getDefault().language}${Locale.getDefault().country}")
                        .header("Authorization", "Bearer ${user?.accessToken}")
                        .build()
        )

    }
}