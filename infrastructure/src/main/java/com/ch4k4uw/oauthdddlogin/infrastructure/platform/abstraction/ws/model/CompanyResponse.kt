package com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.ws.model

data class CompanyResponse(val id: Long,
                           val name: String,
                           val logoUrl: String,
                           val location: String)