package com.chelsenok.bots

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@EnableScheduling
@EnableAsync
open class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
