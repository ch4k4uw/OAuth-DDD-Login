package com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.builder

import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.builder.entity.UserEntityBuilder
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity.UserEntity
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.value.SecurityValueFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.value.CompanyValue
import com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.entity.SimpleUserEntity

class SimpleUserEntityBuilder(private val id: Long, private val companyFactory: SecurityValueFactory): UserEntityBuilder {
    private var name: String? = null
    private var email: String? = null
    private var photo: String? = null
    private var password: String? = null
    private var company: CompanyValue? = null

    override fun withName(name: String): UserEntityBuilder {
        this.name = name
        return this
    }

    override fun withEmail(email: String): UserEntityBuilder {
        this.email = email
        return this
    }

    override fun withPhoto(photo: String): UserEntityBuilder {
        this.photo = photo
        return this
    }

    override fun withPassword(password: String): UserEntityBuilder {
        this.password = password
        return this
    }

    override fun withCompany(company: CompanyValue): UserEntityBuilder {
        this.company = company
        return this
    }

    override fun build(): UserEntity = SimpleUserEntity(
        id,
        name ?: "",
        email ?: "",
        photo ?: "",
        password ?: "",
        company ?: companyFactory.newEmptyCompany()
    )

}