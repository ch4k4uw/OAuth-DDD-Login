package com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.ws.model

import com.google.gson.annotations.SerializedName


data class RequestTokenResponse(@SerializedName("token_type") var tokenType: String?,
                                @SerializedName("expires_in") var expiresIn: Long?,
                                @SerializedName("access_token") var accessToken: String?,
                                @SerializedName("refresh_token") var refreshToken: String?)
