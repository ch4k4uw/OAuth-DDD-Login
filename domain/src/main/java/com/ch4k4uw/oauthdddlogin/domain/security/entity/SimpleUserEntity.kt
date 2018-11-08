package com.ch4k4uw.oauthdddlogin.domain.security.entity

import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.helper.DomainCallQueue
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity.UserEntity
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.service.LoginService
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.service.LogoutService
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.value.CompanyValue

class SimpleUserEntity(id: Long,
                       name: String,
                       email: String,
                       photo: String,
                       password: String,
                       company: CompanyValue
) : UserEntity {

    override var id: Long = id
            private set
    override var name: String = name
            private set
    override var email: String = email
            private set
    override var photo: String = photo
            private set
    override var password: String = password
            private set
    override var company: CompanyValue = company
            private set

    override fun login(service: LoginService, success: (UserEntity) -> Unit, error: (Throwable) -> Unit) {
        DomainCallQueue.enqueue{ callback: (UserEntity) -> Unit, errorCallback ->
            service.login(this, callback, errorCallback)
        }.exec({
            id = it.id
            name = it.name
            email = it.email
            password = it.password
            company = it.company
            success(this)
        }, error)
    }

    override fun logout(service: LogoutService, success: (UserEntity) -> Unit, error: (Throwable) -> Unit) {
        DomainCallQueue.enqueue { callback: (UserEntity) -> Unit, errorCallback ->
            service.logout(this, { callback(this) }, errorCallback)
        }.exec(success, error)
    }
}