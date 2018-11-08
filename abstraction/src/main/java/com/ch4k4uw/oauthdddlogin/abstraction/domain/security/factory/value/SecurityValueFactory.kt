package com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.value

import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.value.CompanyValue

interface SecurityValueFactory {
    fun newEmptyCompany(): CompanyValue

    fun newCompany(logo: String, name: String, location: String): CompanyValue

}