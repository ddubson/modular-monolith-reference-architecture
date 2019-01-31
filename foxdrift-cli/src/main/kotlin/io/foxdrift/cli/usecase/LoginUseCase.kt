package io.foxdrift.cli.usecase

import io.foxdrift.cli.request.CLILoginRequest
import io.foxdrift.kernel.AccessDeniedLoginResponse
import io.foxdrift.kernel.LoginRequestSpec
import java.util.*

class LoginUseCase(private val loginRequestSpec: LoginRequestSpec<CLIInMemoryLoginActionSpec>) {
    fun act(loginRequest: CLILoginRequest): CLILoginResponseSpec {
        if (loginRequest.username.isBlank() || loginRequest.password.isBlank()) {
            return CLILoginResponseSpec(AccessDeniedLoginResponse("Failed to provide username or password"))
        }

        return loginRequestSpec
                .loginRequest(loginRequest.username, loginRequest.password)
                .onLoggedIn { sessionKey: UUID ->
                    println("Login success! $sessionKey")
                }
                .onLoginDenied { reason: String ->
                    println("Login failed: $reason")
                }
                .login()
    }
}