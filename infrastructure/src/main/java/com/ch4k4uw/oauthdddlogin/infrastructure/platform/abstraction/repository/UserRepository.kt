package com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.repository

import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.platform.UserModel
import io.reactivex.Observable

interface UserRepository {
    val loggedUser: Observable<UserModel>
    fun setLoggedUser(user: UserModel): Observable<UserModel>
    fun deleteLoggedUser(): Observable<UserModel>
}