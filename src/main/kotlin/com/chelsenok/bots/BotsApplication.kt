package com.chelsenok.bots

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BotsApplication

fun main(args: Array<String>) {
    SpringApplication.run(BotsApplication::class.java, *args)
}
