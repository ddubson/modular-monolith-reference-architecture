package io.foxdrift.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.foxdrift.kernel.InMemoryUserSessionManager
import io.foxdrift.kernel.UserSessionManager
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {
    @Bean
    fun jsonMapper(): ObjectMapper = ObjectMapper().registerModule(KotlinModule())

    @Bean
    fun userSessionManager(): UserSessionManager = InMemoryUserSessionManager()
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}