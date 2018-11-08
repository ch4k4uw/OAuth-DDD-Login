package com.ch4k4uw.oauthdddlogin.infrastructure.platform.ws.model

import com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.model.platform.UserModel
import java.security.MessageDigest

class SimpleUserModel(override var id: Long,
                      override var serverId: Long,
                      override var name: String,
                      override var userPhoto: String,
                      override var companyId: Long,
                      override var companyLogo: String,
                      override var companyName: String,
                      override var companyLocation: String,
                      override var accessToken: String,
                      override var refreshToken: String,
                      override var accessTokenExpiration: Long): UserModel {
    constructor(): this(0L, 0L,  "", "", 0L, "", "", "", "", "", 0L)

    companion object {
        private val HexTable = "0123456789ABCDEF".toCharArray()
        private fun ByteArray.toHexString(): String {
            val result = CharArray(this.size * 2)
            for(i in 0 until this.size) {
                val v = this[i].toInt() and 0xFF
                result[i * 2] = HexTable[v ushr 4]
                result[i * 2 + 1] = HexTable[v and 0x0F]
            }
            return String(result)
        }

        private fun toMd5Credential(email: String, password: String): String {
            val digest = MessageDigest.getInstance("MD5")
            digest.update("$email:$password".toByteArray())

            return digest.digest().toHexString()

        }
    }

    private var mEmail = ""
    private var mPassword = ""

    override val password: String get() = mPassword
    override val email: String get() = mEmail

    override fun compareCredentials(email: String, password: String)
            = this.password == toMd5Credential(email, password)

    override fun setCredentials(email: String, password: String) {
        mEmail = email
        mPassword = toMd5Credential(email, password)
    }


}