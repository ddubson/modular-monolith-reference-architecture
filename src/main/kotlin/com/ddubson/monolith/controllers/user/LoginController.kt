package com.ddubson.monolith.controllers.user

import com.ddubson.monolith.usecases.user.LoginUseCase
import com.ddubson.monolith.web.requests.LoginRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(val loginUseCase: LoginUseCase) {
    @PostMapping("/")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<*> {
        return loginUseCase.act(loginRequest,
                { ResponseEntity.ok(it) },
                { ResponseEntity.badRequest().body(it) })
    }
}