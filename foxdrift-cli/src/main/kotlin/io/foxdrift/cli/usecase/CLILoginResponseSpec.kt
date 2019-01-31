package io.foxdrift.cli.usecase

import io.foxdrift.kernel.AccessDeniedLoginResponse
import io.foxdrift.kernel.LoginResponse
import io.foxdrift.kernel.LoginResponseSpec
import io.foxdrift.kernel.SuccessfulLoginResponse

class CLILoginResponseSpec(
        private val loginResponse: LoginResponse,
        private val mapSuccess: (SuccessfulLoginResponse) -> String =
                { _ -> throw IllegalStateException("No response set") },
        private val mapDenied: (AccessDeniedLoginResponse) -> String =
                { _ -> throw IllegalStateException("No response set") }) : LoginResponseSpec<String> {
    override fun mapLoginSuccess(map: (SuccessfulLoginResponse) -> String): LoginResponseSpec<String> =
            CLILoginResponseSpec(loginResponse, map, mapDenied)

    override fun mapLoginDenied(map: (AccessDeniedLoginResponse) -> String): LoginResponseSpec<String> =
            CLILoginResponseSpec(loginResponse, mapSuccess, map)

    override fun collectResponse(): String {
        return when (loginResponse) {
            is SuccessfulLoginResponse -> mapSuccess.invoke(loginResponse)
            is AccessDeniedLoginResponse -> mapDenied.invoke(loginResponse)
            else -> throw IllegalStateException()
        }
    }
}