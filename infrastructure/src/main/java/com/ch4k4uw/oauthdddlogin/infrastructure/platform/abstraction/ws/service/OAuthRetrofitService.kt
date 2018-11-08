package com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.ws.service

import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.ws.model.RequestTokenResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OAuthRetrofitService {
    @FormUrlEncoded
    @POST("oauth/access_token")
    fun requestAccessToken(@Field("grant_type") grantType: String,
                           @Field("client_id") clientId: String,
                           @Field("client_secret") clientSecret: String,
                           @Field("scope") scope: String,
                           @Field("username") username: String,
                           @Field("password") password: String): Observable<RequestTokenResponse>

    @FormUrlEncoded
    @POST("oauth/access_token")
    fun refreshToken(@Field("grant_type") grantType: String,
                     @Field("client_id") clientId: String,
                     @Field("refreshToken") refreshToken: String,
                     @Field("client_secret") clientSecret: String,
                     @Field("scope") scope: String): Observable<RequestTokenResponse>

}