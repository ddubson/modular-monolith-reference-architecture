package io.foxdrift.api.usecase

import io.foxdrift.api.request.LoginRequest
import io.foxdrift.kernel.UserSessionManager
import io.foxdrift.model.User
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class LoginUseCase(private val userSessionManager: UserSessionManager) {
    fun act(loginRequest: LoginRequest,
            onSuccess: (User) -> ResponseEntity<*>,
            onValidateFail: (String) -> ResponseEntity<*>) {
        if (loginRequest.username.isNullOrBlank()) {
            onValidateFail.invoke("Username invalid")
            return
        }

        if (loginRequest.password.isNullOrBlank()) {
            onValidateFail.invoke("Password invalid")
            return
        }

        userSessionManager
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