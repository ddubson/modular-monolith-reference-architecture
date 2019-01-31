package io.foxdrift.cli

import io.foxdrift.cli.request.CLILoginRequest
import io.foxdrift.cli.usecase.CLILoginRequestSpec
import io.foxdrift.cli.usecase.LoginUseCase
import io.foxdrift.kernel.AccessDeniedLoginResponse
import io.foxdrift.kernel.SuccessfulLoginResponse
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class CLIApplication(private val loginUseCase: LoginUseCase): CommandLineRunner {
    override fun run(vararg args: String?) {
        login()
    }

    private fun login() {
        println(loginUseCase.act(CLILoginRequest("hello", "world"))
                .mapLoginSuccess { loginResponse: SuccessfulLoginResponse ->
                    "Successfully logged in! ${loginResponse.sessionId}"
                }
                .mapLoginDenied { loginResponse: AccessDeniedLoginResponse ->
                    "Access denied! ${loginResponse.reason}"
                }
                .collectResponse())
    }
}

@Configuration
class ApplicationConfiguration {
    @Bean
    fun loginUseCase(cliLoginRequestSpec: CLILoginRequestSpec): LoginUseCase = LoginUseCase(cliLoginRequestSpec)

    @Bean
    fun loginRequestSpec(): CLILoginRequestSpec = CLILoginRequestSpec()
}

fun main(args: Array<String>) {
    runApplication<CLIApplication>(*args)
}