package com.ddubson.monolith.usecases.user

import com.ddubson.monolith.models.User
import com.ddubson.monolith.web.requests.LoginRequest
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