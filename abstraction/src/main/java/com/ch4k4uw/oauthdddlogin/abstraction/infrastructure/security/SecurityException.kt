package com.ch4k4uw.oauthdddlogin.abstraction.infrastructure.security

import java.lang.Exception

open class SecurityException(message: String, parent: Throwable? = null): Exception("Security error${if (message.isBlank()) "" else ": $message"}", parent)