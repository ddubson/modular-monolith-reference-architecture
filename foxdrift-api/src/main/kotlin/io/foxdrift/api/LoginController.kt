package io.foxdrift.api

import io.foxdrift.api.request.WebLoginRequest
import io.foxdrift.api.usecase.LoginUseCase
import io.foxdrift.kernel.AccessDeniedLoginResponse
import io.foxdrift.kernel.SuccessfulLoginResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(val loginUseCase: LoginUseCase) {
    @PostMapping("/")
    fun login(@RequestBody webLoginRequest: WebLoginRequest): ResponseEntity<*> {
        return loginUseCase.act(webLoginRequest)
                .mapLoginSuccess { loginResponse: SuccessfulLoginResponse ->
                    ResponseEntity.ok(loginResponse)
                }
                .mapLoginDenied { loginResponse: AccessDeniedLoginResponse ->
                    ResponseEntity.badRequest().body(loginResponse)
                }
                .collectResponse()
    }
}