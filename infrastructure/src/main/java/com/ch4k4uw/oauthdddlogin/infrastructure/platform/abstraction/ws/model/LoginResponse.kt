package com.ch4k4uw.oauthdddlogin.infrastructure.platform.abstraction.ws.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (val id: Long,
                          val name: String,
                          val email: String,
                          val photoUrl: String,
                          val permissions: List<String>,
                          @SerializedName("institution") val company: CompanyResponse)