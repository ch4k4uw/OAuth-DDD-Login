package com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.service

import android.annotation.SuppressLint
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity.UserEntity
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.entity.UserEntityBuilderFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.value.SecurityValueFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.service.LoginService
import com.ch4k4uw.oauthdddlogin.abstraction.infrastructure.security.InvalidUserOrPasswordException
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.rest.input.LoginInput
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.service.RestService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class SimpleLoginService @Inject constructor(private val restService: RestService, private val userBuilderFactory: UserEntityBuilderFactory, private val valueFactory: SecurityValueFactory): LoginService {
    @SuppressLint("CheckResult")
    override fun login(user: UserEntity, success: (UserEntity) -> Unit, error: (Throwable) -> Unit) {
        restService
                .login(LoginInput(user.email, user.password))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
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
                    if(it is HttpException) {
                        if(it.code() == 404 || it.code() == 401) {
                            error(InvalidUserOrPasswordException())
                        } else {
                            error(it)
                        }
                    } else {
                        error(it)
                    }
                })
    }
}