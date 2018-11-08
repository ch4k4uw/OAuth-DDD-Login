package com.ch4k4uw.oauthdddlogin.abstraction.domain.security.value

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.value.Value

interface CompanyValue: Value {
    val logo: String
    val name: String
    val location: String

    public override fun clone(): Any {
        return super.clone()
    }

}