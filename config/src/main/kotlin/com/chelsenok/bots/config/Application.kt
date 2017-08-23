package com.chelsenok.bots.config

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
open class Application {
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
