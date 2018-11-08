package com.ch4k4uw.oauthdddlogin.application.dto

import java.io.Serializable

class Company internal constructor() : Serializable, Cloneable, Comparable<Company> {
    override fun compareTo(other: Company): Int
            = "$name-$location-$logo"
                    .compareTo("${other.name}-${other.location}-${other.logo}")

    var logo: String = ""
        internal set
    var name: String = ""
        internal set
    var location: String = ""
        internal set
}

fun company(block: Company.() -> Unit): Company = Company().apply(block)