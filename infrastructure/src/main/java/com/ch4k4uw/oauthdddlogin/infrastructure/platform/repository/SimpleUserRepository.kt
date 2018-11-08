package com.ch4k4uw.oauthdddlogin.infrastructure.platform.repository

import android.content.ContentValues
import android.content.Context
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.exception.NoLoggedUserException
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.platform.UserModel
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.repository.UserRepository
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.provider.UserContentProvider
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Provider

class SimpleUserRepository @Inject constructor(private val context: Context, private val userModelProvider: Provider<UserModel>): UserRepository {

    override val loggedUser: Observable<UserModel>
        get() = Observable
                .just(0)
                .map {
                    val rawResult = context.contentResolver.query(UserContentProvider.UsersUri, null, null, null, null)
                    val result = userModelProvider.get()

                    if(rawResult?.moveToFirst() == true) {
                        result.apply {
                            val iId = rawResult.getColumnIndex("id")
                            val iServerId = rawResult.getColumnIndex("serverId")
                            val iName = rawResult.getColumnIndex("name")
                            val iEmail = rawResult.getColumnIndex("email")
                            val iUserPhoto = rawResult.getColumnIndex("userPhoto")
                            val iPassword = rawResult.getColumnIndex("password")
                            val iCompanyId = rawResult.getColumnIndex("companyId")
                            val iCompanyLogo = rawResult.getColumnIndex("companyLogo")
                            val iCompanyName = rawResult.getColumnIndex("companyName")
                            val iCompanyLocation = rawResult.getColumnIndex("companyLocation")
                            val iAccessToken = rawResult.getColumnIndex("accessToken")
                            val iRefreshToken = rawResult.getColumnIndex("refreshToken")
                            val iAccessTokenExpiration = rawResult.getColumnIndex("accessTokenExpiration")

                            id = rawResult.getLong(iId)
                            serverId = rawResult.getLong(iServerId)
                            name = rawResult.getString(iName) ?: ""
                            userPhoto = rawResult.getString(iUserPhoto) ?: ""
                            companyId = rawResult.getLong(iCompanyId)
                            companyLogo = rawResult.getString(iCompanyLogo) ?: ""
                            companyName = rawResult.getString(iCompanyName) ?: ""
                            companyLocation = rawResult.getString(iCompanyLocation) ?: ""
                            accessToken = rawResult.getString(iAccessToken) ?: ""
                            refreshToken = rawResult.getString(iRefreshToken) ?: ""
                            accessTokenExpiration = rawResult.getLong(iAccessTokenExpiration)
                            setCredentials(rawResult.getString(iEmail) ?: "", rawResult.getString(iPassword) ?: "")
                        }
                    }

                    rawResult?.close()

                    if(result.id == 0L) {
                        throw NoLoggedUserException()
                    }

                    result
                }

    override fun setLoggedUser(user: UserModel): Observable<UserModel>
            = Observable
                .just(0)
                .flatMap {
                    val value = ContentValues().apply {
                        put("id", user.id)
                        put("serverId", user.serverId)
                        put("name", user.name)
                        put("email", user.email)
                        put("userPhoto", user.userPhoto)
                        put("password", user.password)
                        put("companyId", user.companyId)
                        put("companyLogo", user.companyLogo)
                        put("companyName", user.companyName)
                        put("companyLocation", user.companyLocation)
                        put("accessToken", user.accessToken)
                        put("refreshToken", user.refreshToken)
                        put("accessTokenExpiration", user.accessTokenExpiration)
                    }

                    context.contentResolver.insert(UserContentProvider.UsersUri, value)
                    loggedUser
                }

    override fun deleteLoggedUser(): Observable<UserModel>
            = loggedUser
                .flatMap {
                    context.contentResolver.delete(UserContentProvider.usersUriById(it.id), null, null)
                    Observable.just(it)
                }

}