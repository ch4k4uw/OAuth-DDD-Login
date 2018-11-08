package com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.repository

import android.annotation.SuppressLint
import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.repository.specification.ByIdRepositorySpecification
import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.repository.specification.RepositorySpecification
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity.UserEntity
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.entity.UserEntityBuilderFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.value.SecurityValueFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.repository.UserQueryRepository
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.repository.specification.LoggedUserSpecification
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.exception.NoLoggedUserException
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.repository.UserRepository
import java.security.InvalidParameterException
import javax.inject.Inject

class SimpleUserQueryRepository @Inject constructor(private val userRepository: UserRepository, private val userBuilderFactory: UserEntityBuilderFactory, private val valueFactory: SecurityValueFactory): UserQueryRepository {
    @SuppressLint("CheckResult")
    override fun getById(spec: ByIdRepositorySpecification<UserEntity, Long>, success: (UserEntity) -> Unit, error: (Throwable) -> Unit) {
        if(spec is LoggedUserSpecification) {
            userRepository
                    .loggedUser
                    .subscribe({
                        success(
                                userBuilderFactory.newBuilder(it.id)
                                        .withName(it.name)
                                        .withEmail(it.email)
                                        .withPhoto(it.userPhoto)
                                        .withPassword(it.password)
                                        .withCompany(valueFactory.newCompany(
                                                logo = it.companyLogo,
                                                name = it.companyName,
                                                location = it.companyLocation
                                        ))
                                        .build()
                        )
                    }, {
                        if(it is NoLoggedUserException) {
                            error(com.ch4k4uw.oauthdddlogin.abstraction.infrastructure.security.NoLoggedUserException(it))
                        } else {
                            error(it)
                        }
                    })
        } else {
            throw InvalidParameterException("Unexpected specification on ${this.javaClass.simpleName}")
        }
    }

    override fun find(spec: RepositorySpecification<UserEntity, Long>, success: (List<UserEntity>) -> Unit, error: (Throwable) -> Unit) {
        throw InvalidParameterException("Unexpected specification on ${this.javaClass.simpleName}")
    }
}