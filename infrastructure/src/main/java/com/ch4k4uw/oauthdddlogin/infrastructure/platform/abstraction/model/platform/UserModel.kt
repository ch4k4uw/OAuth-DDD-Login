package com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.platform

interface UserModel {
    var id: Long
    var serverId: Long
    var name: String
    val email: String
    var userPhoto: String
    val password: String
    var companyId: Long
    var companyLogo: String
    var companyName: String
    var companyLocation: String
    var accessToken: String
    var refreshToken: String
    var accessTokenExpiration: Long

    fun compareCredentials(email: String, password: String): Boolean

    fun setCredentials(email: String, password: String)


}