package io.foxdrift.api.usecase

import io.foxdrift.kernel.AccessDeniedLoginResponse
import io.foxdrift.kernel.LoginActionSpec
import io.foxdrift.kernel.LoginRequest
import io.foxdrift.kernel.SuccessfulLoginResponse
import java.util.*

class WebInMemoryLoginActionSpec(val loginRequest: LoginRequest,
                                 val loginSuccess: (UUID) -> Unit = { _ -> },
                                 val loginDenied: (String) -> Unit = { _ -> }) : LoginActionSpec<WebLoginResponseSpec> {
    override fun onLoggedIn(loginSuccess: (UUID) -> Unit): WebInMemoryLoginActionSpec =
            WebInMemoryLoginActionSpec(this.loginRequest, loginSuccess, this.loginDenied)

    override fun onLoginDenied(loginDenied: (String) -> Unit): WebInMemoryLoginActionSpec =
            WebInMemoryLoginActionSpec(this.loginRequest, this.loginSuccess, loginDenied)

    override fun login(): WebLoginResponseSpec {
        return if (loginRequest.username != "hello" && loginRequest.password != "world") {
            loginDenied("Invalid username or password")
            WebLoginResponseSpec(AccessDeniedLoginResponse("Invalid username or password"))
        } else {
            val sessionKey = UUID.randomUUID()
            loginSuccess(sessionKey)
            WebLoginResponseSpec(SuccessfulLoginResponse(sessionKey))
        }
    }
}