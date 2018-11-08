package com.ch4k4uw.oauthdddlogin.infrastructure.platform.ws.service

import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.config.InfraConfig
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.platform.UserModel
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.rest.input.LoginInput
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.repository.UserRepository
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.service.RestService
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.ws.interceptor.AuthInterceptor
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.ws.model.LoginResponse
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.ws.model.RequestTokenResponse
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.ws.service.OAuthRetrofitService
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.ws.service.RetrofitService
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class SimpleRestService @Inject constructor(private val config: InfraConfig,
                                            private val userRepository: UserRepository,
                                            private val userModelProvider: Provider<UserModel>,
                                            authInterceptor: AuthInterceptor?): RestService {
    private val retrofitService: RetrofitService
    private val oauthRetrofitService: OAuthRetrofitService

    init {
        val callAdapter = RxJava2CallAdapterFactory.create()

        @Suppress("SpellCheckingInspection")
        val gson = GsonBuilder().setDateFormat(config.gsonDateFormat).create()
        val retrofitBuilder = Retrofit
                .Builder()
                .baseUrl(config.baseRestUrl)
                .addCallAdapterFactory(callAdapter)
                .addConverterFactory(GsonConverterFactory.create(gson))

        if(authInterceptor != null) {
            retrofitBuilder
                    .client(OkHttpClient.Builder()
                            .addInterceptor(authInterceptor)
                            .addInterceptor(HttpLoggingInterceptor().apply {
                                level = HttpLoggingInterceptor.Level.BASIC
                            })
                            .build()
                    )

            authInterceptor.refreshTokenHandler = {refreshToken, errorCallback ->
                refreshToken(refreshToken)
                        .flatMap { userRepository.loggedUser }
                        .doOnError {
                            errorCallback(it)
                        }
                        .blockingSingle()
            }
        }

        retrofitService = retrofitBuilder
                .build()
                .create(RetrofitService::class.java)

        oauthRetrofitService = Retrofit
                .Builder()
                .baseUrl(config.baseRestUrl)
                .addCallAdapterFactory(callAdapter)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BASIC
                        })
                        .build()
                )
                .build()
                .create(OAuthRetrofitService::class.java)


    }

    override fun login(credentials: LoginInput): Observable<UserModel>
            = oauthRetrofitService.requestAccessToken(
                    config.oAuthGrantType,
                    config.oAuthClientId,
                    config.oAuthClientSecret,
                    config.oAuthScope,
                    credentials.email,
                    credentials.password)
                    .flatMap {
                        val user = userModelProvider.get()
                        user.accessToken = it.accessToken ?: ""
                        user.refreshToken = it.refreshToken ?: ""
                        user.accessTokenExpiration = (it.expiresIn ?: 0) * 1000 + System.currentTimeMillis()

                        userRepository.setLoggedUser(user)
                    }.flatMap {
                        retrofitService.login(credentials)
                    }.flatMap {
                        Observable.zip(
                                Observable.just(it),
                                userRepository.loggedUser,
                                BiFunction<LoginResponse, UserModel, UserModel>{ loginResponse, userModel ->
                                    userModel.serverId = loginResponse.id
                                    userModel.name = loginResponse.name
                                    userModel.userPhoto = loginResponse.photoUrl
                                    userModel.companyId = loginResponse.company.id
                                    userModel.companyLogo = loginResponse.company.logoUrl
                                    userModel.companyName = loginResponse.company.name
                                    userModel.companyLocation = loginResponse.company.location
                                    userModel.setCredentials(loginResponse.email, credentials.password)
                                    userModel
                                }
                        )
                    }.flatMap {
                        userRepository.setLoggedUser(it)
                    }


    override fun refreshToken(refreshToken: String): Observable<UserModel>
            = Observable.zip(oauthRetrofitService.refreshToken(config.oAuthRefreshGrantType, config.oAuthClientId, refreshToken, config.oAuthClientSecret, config.oAuthScope), userRepository.loggedUser, BiFunction<RequestTokenResponse, UserModel, UserModel>{ t1, t2 ->
                t2.accessToken = t1.accessToken ?: ""
                t2.refreshToken = t1.refreshToken ?: ""
                t2.accessTokenExpiration = (t1.expiresIn ?: 0L) * 1000 + System.currentTimeMillis()
                t2
            }).flatMap { userRepository.setLoggedUser(it) }



}