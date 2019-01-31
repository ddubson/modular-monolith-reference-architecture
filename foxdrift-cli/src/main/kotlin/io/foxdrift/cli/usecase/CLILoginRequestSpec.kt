package io.foxdrift.cli.usecase

import io.foxdrift.kernel.LoginRequest
import io.foxdrift.kernel.LoginRequestSpec

class CLILoginRequestSpec : LoginRequestSpec<CLIInMemoryLoginActionSpec> {
    override fun loginRequest(username: String, password: String): CLIInMemoryLoginActionSpec =
            CLIInMemoryLoginActionSpec(LoginRequest(username, password))
}