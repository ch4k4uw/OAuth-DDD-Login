package com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.config

interface InfraConfig {
    val oAuthGrantType: String
    val oAuthRefreshGrantType: String
    val oAuthClientId: String
    val oAuthClientSecret: String
    val oAuthScope: String
    val gsonDateFormat: String
    val baseRestUrl: String
}