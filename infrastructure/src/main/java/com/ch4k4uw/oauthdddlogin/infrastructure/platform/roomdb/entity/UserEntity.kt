package com.ch4k4uw.oauthdddlogin.infrastructure.platform.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "security_user")
open class UserEntity @Ignore constructor(id: Long) {
    constructor(): this(0)
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = id
    @ColumnInfo(name = "server_id")
    var serverId: Long = 0L
    @ColumnInfo(name = "name", collate = ColumnInfo.NOCASE)
    var name: String = ""
    @ColumnInfo(name = "email", collate = ColumnInfo.NOCASE)
    var email: String = ""
    @ColumnInfo(name = "user_photo", collate = ColumnInfo.NOCASE)
    var userPhoto: String = ""
    @ColumnInfo(name = "password", collate = ColumnInfo.NOCASE)
    var password: String = ""
    @ColumnInfo(name = "company_id")
    var companyId: Long = 0L
    @ColumnInfo(name = "company_logo", collate = ColumnInfo.NOCASE)
    var companyLogo: String = ""
    @ColumnInfo(name = "company_name", collate = ColumnInfo.NOCASE)
    var companyName: String = ""
    @ColumnInfo(name = "company_location", collate = ColumnInfo.NOCASE)
    var companyLocation: String = ""
    @ColumnInfo(name = "access_token", collate = ColumnInfo.NOCASE)
    var accessToken: String = ""
    @ColumnInfo(name = "refresh_token", collate = ColumnInfo.NOCASE)
    var refreshToken: String = ""
    @ColumnInfo(name = "access_token_expiration")
    var accessTokenExpiration: Long = 0L



}