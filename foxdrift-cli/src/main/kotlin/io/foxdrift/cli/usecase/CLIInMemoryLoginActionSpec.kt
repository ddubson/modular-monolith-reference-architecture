package io.foxdrift.cli.usecase

import io.foxdrift.kernel.AccessDeniedLoginResponse
import io.foxdrift.kernel.LoginActionSpec
import io.foxdrift.kernel.LoginRequest
import io.foxdrift.kernel.SuccessfulLoginResponse
import java.util.*

class CLIInMemoryLoginActionSpec(val loginRequest: LoginRequest,
                                 val loginSuccess: (UUID) -> Unit = { _ -> },
                                 val loginDenied: (String) -> Unit = { _ -> }) : LoginActionSpec<CLILoginResponseSpec> {
    override fun onLoggedIn(loginSuccess: (UUID) -> Unit): LoginActionSpec<CLILoginResponseSpec> =
            CLIInMemoryLoginActionSpec(loginRequest, loginSuccess, loginDenied)

    override fun onLoginDenied(loginDenied: (String) -> Unit): LoginActionSpec<CLILoginResponseSpec> =
            CLIInMemoryLoginActionSpec(loginRequest, loginSuccess, loginDenied)

    override fun login(): CLILoginResponseSpec {
        return if (loginRequest.username != "hello" && loginRequest.password != "world") {
            loginDenied("Invalid username or password")
            CLILoginResponseSpec(AccessDeniedLoginResponse("Invalid username or password"))
        } else {
            val sessionKey = UUID.randomUUID()
            loginSuccess(sessionKey)
            CLILoginResponseSpec(SuccessfulLoginResponse(sessionKey))
        }
    }
}