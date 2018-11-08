package com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.specification

import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.repository.specification.LoggedUserSpecification

interface SecuritySpecificationFactory {
    fun newLoggedUserSpecification(): LoggedUserSpecification
}