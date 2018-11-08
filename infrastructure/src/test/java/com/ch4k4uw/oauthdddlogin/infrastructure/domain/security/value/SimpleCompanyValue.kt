package com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.value

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.value.Value
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.value.CompanyValue

class SimpleCompanyValue(override val logo: String, override val name: String, override val location: String) : CompanyValue {
    companion object {
        private fun CompanyValue.internalCompareTo(other: Value): Int =
                if(other is CompanyValue) {
                    "$logo-$name-$location".compareTo("${other.logo}-${other.name}-${other.location}")
                } else javaClass.simpleName.compareTo(other.javaClass.simpleName)

        val Empty = object: CompanyValue {
            override val logo: String
                get() = ""
            override val name: String
                get() = ""
            override val location: String
                get() = ""

            override fun compareTo(other: Value): Int
                    = internalCompareTo(other)

        }
    }

    override fun compareTo(other: Value): Int
            = internalCompareTo(other)
}