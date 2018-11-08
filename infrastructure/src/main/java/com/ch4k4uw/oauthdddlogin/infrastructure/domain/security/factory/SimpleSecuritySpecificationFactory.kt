package com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.factory

import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.specification.SecuritySpecificationFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.repository.specification.LoggedUserSpecification
import com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.repository.specification.SimpleLoggedUserSpecification
import javax.inject.Inject

class SimpleSecuritySpecificationFactory @Inject constructor(): SecuritySpecificationFactory {
    override fun newLoggedUserSpecification(): LoggedUserSpecification
            = SimpleLoggedUserSpecification()
}