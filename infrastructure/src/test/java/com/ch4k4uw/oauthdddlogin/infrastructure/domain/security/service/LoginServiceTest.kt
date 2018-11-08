package com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.service

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity.UserEntity
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.factory.entity.UserEntityBuilderFactory
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.service.LoginService
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.service.LogoutService
import com.ch4k4uw.oauthdddlogin.infrastructure.R
import com.ch4k4uw.oauthdddlogin.infrastructure.ioc.DaggerInfraComponent
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.provider.UserContentProvider
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.roomdb.AppDatabase
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowContentResolver

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class LoginServiceTest {
    private lateinit var loginService: LoginService
    private lateinit var logoutService: LogoutService
    private lateinit var userBuilderFactory: UserEntityBuilderFactory
    private lateinit var password: String

    @Before
    fun setUp() {
        ShadowContentResolver.registerProviderInternal(
                UserContentProvider.Authority,
                Robolectric.setupContentProvider(UserContentProvider::class.java)
        )

        RxJavaPlugins.setIoSchedulerHandler { AndroidSchedulers.mainThread() }
        RxJavaPlugins.setComputationSchedulerHandler { AndroidSchedulers.mainThread() }
        RxJavaPlugins.setNewThreadSchedulerHandler { AndroidSchedulers.mainThread() }

        val component = DaggerInfraComponent
                .builder()
                .context(ApplicationProvider.getApplicationContext())
                .build()

        loginService = component.loginService
        logoutService = component.logoutService
        userBuilderFactory = component.userEntityBuilder
        password = ApplicationProvider.getApplicationContext<Context>().getString(R.string.oauth_pass)


    }

    @After
    fun tearDown() {
        AppDatabase.Instance.close()
    }

    @Test
    fun login() {
        val user = userBuilderFactory
                .newBuilder(0)
                .withEmail(ApplicationProvider.getApplicationContext<Context>().getString(R.string.oauth_user))
                .withPassword(password).build()

        var error: Throwable? = null
        var result: UserEntity? = null

        user.login(loginService, {
            result = it
        }, {
            error = it
        })

        if(error != null) {
            error?.printStackTrace()
        }
        assertNull("Shouldn't have error", error)
        assertNotNull("Should return an user", result)
        assertNotEquals("Must be a valid user ", 0L, user.id)
        assertNotEquals("Password must be hashed",
            password, user.password)

        System.out.println(GsonBuilder().setPrettyPrinting().create().toJson(result))

        error = null
        logoutService.logout(result!!, { }, { error = it })

        assertNull("Shouldn't have error", error)

    }
}