package io.foxdrift.api

import io.foxdrift.api.request.LoginRequest
import io.foxdrift.api.usecase.LoginUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(val loginUseCase: LoginUseCase) {
    @PostMapping("/")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<*> {
        return loginUseCase.act(loginRequest)
                .mapLoginSuccess<ResponseEntity<*>> { loginResponse ->

                }
                .mapLoginDenied<ResponseEntity<*>> { loginResponse ->

                }

    }
}