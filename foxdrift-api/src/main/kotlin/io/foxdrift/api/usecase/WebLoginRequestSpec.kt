package io.foxdrift.api.usecase

import io.foxdrift.kernel.LoginRequest
import io.foxdrift.kernel.LoginRequestSpec

class WebLoginRequestSpec : LoginRequestSpec<WebInMemoryLoginActionSpec> {
    override fun loginRequest(username: String, password: String): WebInMemoryLoginActionSpec =
            WebInMemoryLoginActionSpec(LoginRequest(username, password))
}