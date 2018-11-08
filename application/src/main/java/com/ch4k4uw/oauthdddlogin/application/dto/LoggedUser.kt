package com.ch4k4uw.oauthdddlogin.application.dto

import java.io.Serializable

class LoggedUser internal constructor(): Serializable, Cloneable, Comparable<LoggedUser> {
    override fun compareTo(other: LoggedUser): Int
            = "$name-$email-$photo-$password-${company.name}-${company.location}-${company.logo}"
                    .compareTo("${other.name}-${other.email}-${other.photo}-${other.password}-${other.company.name}-${other.company.location}-${other.company.logo}")

    var name: String = ""
    var email: String = ""
    var photo: String = ""
    var password: String = ""
    var company: Company = Company()
}

fun loggedUser(block: LoggedUser.() -> Unit): LoggedUser = LoggedUser().apply(block)