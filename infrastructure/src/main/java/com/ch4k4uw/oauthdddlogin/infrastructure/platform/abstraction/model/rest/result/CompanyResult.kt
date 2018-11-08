package com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.rest.result

data class CompanyResult(var id: Long,
                         var name: String,
                         var logoUrl: String,
                         var location: String) {
    constructor(): this(0L, "", "", "")
}