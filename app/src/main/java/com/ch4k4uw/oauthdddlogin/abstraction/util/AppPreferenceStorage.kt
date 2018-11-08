package com.ch4k4uw.oauthdddlogin.abstraction.util

import io.reactivex.Observable

interface AppPreferenceStorage {
    var login: Observable<String>
    var password: Observable<String>
    var rememberCredentials: Observable<Boolean>
}