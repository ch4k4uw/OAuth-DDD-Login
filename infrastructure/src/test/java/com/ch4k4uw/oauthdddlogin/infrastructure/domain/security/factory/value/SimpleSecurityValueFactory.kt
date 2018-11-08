package com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.factory.value

import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.value.SecurityValueFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.value.CompanyValue
import com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.value.SimpleCompanyValue
import javax.inject.Inject

class SimpleSecurityValueFactory @Inject constructor(): SecurityValueFactory {
    override fun newEmptyCompany(): CompanyValue
            = SimpleCompanyValue.Empty

    override fun newCompany(logo: String, name: String, location: String): CompanyValue
            = SimpleCompanyValue(logo, name, location)
}