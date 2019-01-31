package io.foxdrift.api.usecase

import io.foxdrift.api.request.LoginRequest
import io.foxdrift.kernel.LoginResponse
import io.foxdrift.kernel.UserSessionManager
import io.foxdrift.model.User
import org.springframework.stereotype.Component

@Component
class LoginUseCase(private val userSessionManager: UserSessionManager) {
    fun act(loginRequest: LoginRequest,
            onSuccess: (User) -> LoginResponse,
            onValidateFail: (String) -> LoginResponse): LoginUseCaseResponseSpec {
        if (loginRequest.username.isNullOrBlank()) {
            return onValidateFail.invoke("Username invalid")
        }

        if (loginRequest.password.isNullOrBlank()) {
            return onValidateFail.invoke("Password invalid")
        }

        return userSessionManager
                .loginRequest(loginRequest.username, loginRequest.password)
                .onLoggedIn { sessionKey ->
                    onSuccess.invoke(User("hello", "world"))
                }
                .onLoginDenied { reason ->
                    println("Login failed: $reason")
                }
                .login()
    }
}