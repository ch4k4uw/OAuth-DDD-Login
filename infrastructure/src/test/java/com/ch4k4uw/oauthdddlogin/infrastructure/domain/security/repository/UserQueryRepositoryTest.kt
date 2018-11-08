package com.ch4k4uw.oauthdddlogin.infrastructure.domain.security.repository

import androidx.test.core.app.ApplicationProvider
import com.ch4k4uw.oauthdddlogin.abstraction.domain.base.repository.specification.RepositorySpecification
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.entity.UserEntity
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.repository.UserQueryRepository
import com.ch4k4uw.oauthdddlogin.abstraction.domain.security.repository.specification.LoggedUserSpecification
import com.ch4k4uw.oauthdddlogin.abstraction.infrastructure.security.NoLoggedUserException
import com.ch4k4uw.oauthdddlogin.infrastructure.ioc.DaggerInfraComponent
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.provider.UserContentProvider
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.roomdb.AppDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowContentResolver
import java.security.InvalidParameterException

@RunWith(RobolectricTestRunner::class)
class UserQueryRepositoryTest {
    private lateinit var repository: UserQueryRepository

    companion object {
        const val PASSWORD = "123456"
    }

    @Before
    fun setUp() {
        ShadowContentResolver.registerProviderInternal(
                UserContentProvider.Authority,
                Robolectric.setupContentProvider(UserContentProvider::class.java)
        )

        RxJavaPlugins.setIoSchedulerHandler { AndroidSchedulers.mainThread() }
        RxJavaPlugins.setComputationSchedulerHandler { AndroidSchedulers.mainThread() }
        RxJavaPlugins.setNewThreadSchedulerHandler { AndroidSchedulers.mainThread() }

        repository = DaggerInfraComponent
                .builder()
                .context(ApplicationProvider.getApplicationContext())
                .build().userRepository

    }

    @After
    fun tearDown() {
        AppDatabase.Instance.close()
    }

    @Test
    fun getById() {
        var error: Throwable? = null
        repository.getById(Mockito.mock(LoggedUserSpecification::class.java), { }, { error = it })

        if(error !is NoLoggedUserException) {
            error?.printStackTrace()
        }

        assertTrue("Should return ${NoLoggedUserException::class.java.simpleName}", error is NoLoggedUserException)

        addUser()

        error = null

        var user: UserEntity? = null
        repository.getById(Mockito.mock(LoggedUserSpecification::class.java), { user = it }, { error = it })

        error?.printStackTrace()
        assertNull("Shouldn't have error", error)
        assertNotNull("Should return an user", user)
        assertTrue("Should be a valid user", user!!.id != 0L)
        assertNotEquals("Password must be hashed",
            PASSWORD, user!!.password)

    }

    @Test(expected = InvalidParameterException::class)
    fun find() {
        @Suppress("UNCHECKED_CAST")
        repository.find(Mockito.mock(RepositorySpecification::class.java) as RepositorySpecification<UserEntity, Long>, { }, { })
    }

    private fun addUser() {
        AppDatabase.Instance.userDao.insert(com.ch4k4uw.oauthdddlogin.infrastructure.platform.roomdb.entity.UserEntity().apply {
            name = "Test User"
            companyName = "Test Company"
            email = "test@user.com"
            password = PASSWORD
        })
    }


}