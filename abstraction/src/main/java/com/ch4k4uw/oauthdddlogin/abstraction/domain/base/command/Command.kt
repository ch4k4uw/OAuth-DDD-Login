package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.command

/**
 *
 */
interface Command {
    /**
     *
     */
    fun exec(complete: () -> Unit)
}