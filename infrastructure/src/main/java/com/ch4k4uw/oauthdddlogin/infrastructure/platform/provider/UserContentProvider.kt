package com.ch4k4uw.oauthdddlogin.infrastructure.platform.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.roomdb.AppDatabase
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.roomdb.dao.UserDao
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.roomdb.entity.UserEntity

class UserContentProvider : ContentProvider() {
    private lateinit var dao: UserDao

    companion object {
        private const val User = 10
        private const val UserId = 20

        const val Authority = "com.ch4k4uw.oauthdddlogin.security"

        @Suppress("unused")
        @JvmField
        val ContentUri = Uri.parse("content://$Authority")!!

        @JvmField
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        private const val UsersPath = "users"

        @JvmField
        val UsersUri = Uri.parse("$ContentUri/$UsersPath")!!

        fun usersUriById(id: Long)
                = Uri.parse("$UsersUri/$id")!!

        init {
            uriMatcher.addURI(Authority, UsersPath, User)
            uriMatcher.addURI(Authority, "$UsersPath/#", UserId)
        }

    }

    @Synchronized
    override fun onCreate(): Boolean {
        dao = AppDatabase.initDatabase(this.context!!).userDao
        return true

    }

    @Synchronized
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        dao.delete(UserEntity(uri.lastPathSegment?.toLong() ?: 0))
        return dao.getChanges()
    }

    override fun getType(uri: Uri): String? = when(uriMatcher.match(uri)) {
        User, UserId -> "User"
        else -> ""
    }

    @Synchronized
    override fun insert(uri: Uri, values: ContentValues): Uri? {
        if(uriMatcher.match(uri) != User) {
            throw RuntimeException("Invalid URI")
        }

        dao.insert(UserEntity(values.getAsLong("id")).apply {
            serverId = values.getAsLong("serverId") ?: 0L
            name = values.getAsString("name") ?: ""
            email = values.getAsString("email") ?: ""
            userPhoto = values.getAsString("userPhoto") ?: ""
            password = values.getAsString("password") ?: ""
            companyId = values.getAsLong("companyId") ?: 0L
            companyLogo = values.getAsString("companyLogo") ?: ""
            companyName = values.getAsString("companyName") ?: ""
            companyLocation = values.getAsString("companyLocation") ?: ""
            accessToken = values.getAsString("accessToken") ?: ""
            refreshToken = values.getAsString("refreshToken") ?: ""
            accessTokenExpiration = values.getAsLong("accessTokenExpiration") ?: 0L
        })

        return Uri.parse("$ContentUri/${if(values.getAsLong("id") == 0L) dao.getLastId() else values.getAsLong("id")}")

    }


    @Synchronized
    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        when(uriMatcher.match(uri)) {
            User, UserId -> {
                val id = if (uriMatcher.match(uri) == UserId) uri.lastPathSegment?.toLong() ?: 0 else 0L
                val users = if (id != 0L) listOf(dao.getUserById(id)) else dao.getAllUsers()

                return MatrixCursor(arrayOf(
                        "id",
                        "serverId",
                        "name",
                        "email",
                        "userPhoto",
                        "password",
                        "companyId",
                        "companyLogo",
                        "companyName",
                        "companyLocation",
                        "accessToken",
                        "refreshToken",
                        "accessTokenExpiration"
                )).apply {
                    for(user in users) {
                        newRow()
                                .add(user.id)
                                .add(user.serverId)
                                .add(user.name)
                                .add(user.email)
                                .add(user.userPhoto)
                                .add(user.password)
                                .add(user.companyId)
                                .add(user.companyLogo)
                                .add(user.companyName)
                                .add(user.companyLocation)
                                .add(user.accessToken)
                                .add(user.refreshToken)
                                .add(user.accessTokenExpiration)
                    }
                }

            }
            else -> throw RuntimeException("Invalid URI")
        }
    }

    @Synchronized
    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int = when(uriMatcher.match(uri)) {
        UserId -> {
            if(values == null) {
                throw RuntimeException("Value can't be null")
            }

            val id = uri.lastPathSegment?.toLong() ?: 0
            val user = dao.getUserById(id)

            dao.insert(UserEntity(id).apply {
                serverId = values.getAsLong("serverId") ?: if (values.containsKey("serverId")) 0L else user.serverId
                name = values.getAsString("name") ?: if (values.containsKey("name")) "" else user.name
                email = values.getAsString("email") ?: if (values.containsKey("email")) "" else user.email
                userPhoto = values.getAsString("userPhoto") ?: if (values.containsKey("userPhoto")) "" else user.userPhoto
                password = values.getAsString("password") ?: if (values.containsKey("password")) "" else user.password
                companyId = values.getAsLong("companyId") ?: if (values.containsKey("companyId")) 0L else user.companyId
                companyLogo = values.getAsString("companyLogo") ?: if (values.containsKey("companyLogo")) "" else user.companyLogo
                companyName = values.getAsString("companyName") ?: if (values.containsKey("companyName")) "" else user.companyName
                companyLocation = values.getAsString("companyLocation") ?: if (values.containsKey("companyLocation")) "" else user.companyLocation
                accessToken = values.getAsString("accessToken") ?: if (values.containsKey("accessToken")) "" else user.accessToken
                refreshToken = values.getAsString("refreshToken") ?: if (values.containsKey("refreshToken")) "" else user.refreshToken
                accessTokenExpiration = values.getAsLong("accessTokenExpiration") ?: if (values.containsKey("accessTokenExpiration")) 0L else user.accessTokenExpiration
            })

            dao.getChanges()

        }
        else -> throw RuntimeException("Invalid URI")
    }
}
