package com.chelsenok.bots.application

import com.chelsenok.youtube.YouTube
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableJpaRepositories("com.chelsenok.bots.repository")
@ComponentScan("com.chelsenok.bots")
@EntityScan("com.chelsenok.bots.repository.entities")
@EnableScheduling
@EnableAsync
@Configuration
@EnableAutoConfiguration
open class Application {

    @Bean
    open fun modelMapping(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STRICT
        return modelMapper
    }

    @Bean
    open fun youtube() = YouTube()

    @Bean
    open fun logger(): Logger = LogManager.getLogger()
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
