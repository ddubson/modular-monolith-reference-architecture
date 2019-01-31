package io.foxdrift.api.usecase

import io.foxdrift.api.request.LoginRequest
import io.foxdrift.kernel.AccessDeniedLoginResponse
import io.foxdrift.kernel.LoginRequestSpec
import org.springframework.stereotype.Component
import java.util.*

@Component
class LoginUseCase(private val loginRequestSpec: LoginRequestSpec<WebInMemoryLoginActionSpec>) {
    fun act(loginRequest: LoginRequest): WebLoginResponseSpec {
        if (loginRequest.username.isNullOrBlank() || loginRequest.password.isNullOrBlank()) {
            return WebLoginResponseSpec(AccessDeniedLoginResponse("Failed to provide username or password"))
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