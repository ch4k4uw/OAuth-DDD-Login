package com.ch4k4uw.oauthdddlogin.infrastructure.platform.ws.model

import org.junit.Assert.*
import org.junit.Test

class SimpleUserModelTest {

    companion object {
        const val USER = "dev@dev.com"
        const val PASS = "123456"
    }

    @Test
    fun compareCredentials() {
        val userModel = SimpleUserModel(
            1,
            2,
            "3",
            "4",
            5,
            "6",
            "7",
            "8",
            "9",
            "10",
            11
        )
        userModel.setCredentials(
            USER,
            PASS
        )

        assertNotEquals("Shouldn't be the same password",
            PASS, userModel.password)
        assertTrue("Should confirm credentials", userModel.compareCredentials(
            USER,
            PASS
        ))
        assertFalse("Shouldn't confirm credentials", userModel.compareCredentials(USER, userModel.password))

    }
}