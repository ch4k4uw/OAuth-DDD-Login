package com.ch4k4uw.oauthdddlogin.abstraction.infrastructure.security

class NoLoggedUserException(parent: Throwable? = null): SecurityException("No logged user", parent)