package io.foxdrift.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.foxdrift.api.usecase.WebInMemoryLoginActionSpec
import io.foxdrift.api.usecase.WebLoginRequestSpec
import io.foxdrift.kernel.LoginRequestSpec
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {
    @Bean
    fun jsonMapper(): ObjectMapper = ObjectMapper().registerModule(KotlinModule())

    @Bean
    fun loginRequestSpec(): LoginRequestSpec<WebInMemoryLoginActionSpec> = WebLoginRequestSpec()
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}