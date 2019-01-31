package io.foxdrift.api.usecase

import io.foxdrift.kernel.AccessDeniedLoginResponse
import io.foxdrift.kernel.LoginResponse
import io.foxdrift.kernel.LoginResponseSpec
import io.foxdrift.kernel.SuccessfulLoginResponse
import org.springframework.http.ResponseEntity

class WebLoginResponseSpec(
        private val loginResponse: LoginResponse,
        private val mapSuccess: (SuccessfulLoginResponse) -> ResponseEntity<*> =
                { _ -> throw IllegalStateException("No response set") },
        private val mapDenied: (AccessDeniedLoginResponse) -> ResponseEntity<*> =
                { _ -> throw IllegalStateException("No response set") }) : LoginResponseSpec<ResponseEntity<*>> {
    override fun mapLoginSuccess(map: (SuccessfulLoginResponse) -> ResponseEntity<*>): LoginResponseSpec<ResponseEntity<*>> =
            WebLoginResponseSpec(this.loginResponse, map, this.mapDenied)

    override fun mapLoginDenied(map: (AccessDeniedLoginResponse) -> ResponseEntity<*>): LoginResponseSpec<ResponseEntity<*>> =
            WebLoginResponseSpec(this.loginResponse, mapSuccess, map)

    override fun collectResponse(): ResponseEntity<*> {
        return when (loginResponse) {
            is SuccessfulLoginResponse -> mapSuccess.invoke(loginResponse)
            is AccessDeniedLoginResponse -> mapDenied.invoke(loginResponse)
            else -> throw IllegalStateException()
        }
    }
}