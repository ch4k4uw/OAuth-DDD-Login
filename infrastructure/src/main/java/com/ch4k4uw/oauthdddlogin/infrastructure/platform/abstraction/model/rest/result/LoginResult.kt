package com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.rest.result

data class LoginResult(val id: Long,
                       val name: String,
                       val email: String,
                       val photoUrl: String,
                       val permissions: List<String>,
                       val company: CompanyResult,
                       val accessToken: String,
                       val refreshToken: String,
                       val expiresIn: Long) {
    constructor(): this(0L, "", "", "", listOf(), CompanyResult(), "", "", 0L)
}