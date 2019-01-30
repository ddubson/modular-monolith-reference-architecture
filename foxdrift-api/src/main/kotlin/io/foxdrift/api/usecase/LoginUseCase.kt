package io.foxdrift.api.usecase

import io.foxdrift.api.request.LoginRequest
import io.foxdrift.model.User
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class LoginUseCase {
    fun act(loginRequest: LoginRequest,
            onSuccess: (User) -> ResponseEntity<*>,
            onValidateFail: (String) -> ResponseEntity<*>): ResponseEntity<*> {
        if (loginRequest.username.isNullOrBlank()) {
            return onValidateFail.invoke("Username invalid")
        }

        return onSuccess.invoke(User("John", "Doe"))
    }
}