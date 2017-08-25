package com.chelsenok.bots.auth.application

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories("com.chelsenok.bots.auth.repository")
@ComponentScan("com.chelsenok.bots.auth")
@EntityScan("com.chelsenok.bots.auth.repository.entities")
@Configuration
@EnableAutoConfiguration
//@EnableDiscoveryClient
open class Application {

    @Bean
    open fun modelMapping(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STRICT
        return modelMapper
    }

    @Bean
    open fun logger(): Logger = LogManager.getLogger()
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
