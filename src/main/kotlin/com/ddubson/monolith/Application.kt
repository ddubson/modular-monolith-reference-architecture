package com.ddubson.monolith

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {
    @Bean
    fun jsonMapper(): ObjectMapper {
        return ObjectMapper().registerModule(KotlinModule())
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}