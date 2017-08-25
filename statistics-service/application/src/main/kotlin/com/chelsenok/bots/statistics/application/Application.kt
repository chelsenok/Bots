package com.chelsenok.bots.statistics.application

import com.chelsenok.bots.statistics.web.AppSchema
import com.chelsenok.youtube.YouTube
import graphql.GraphQL
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableJpaRepositories("com.chelsenok.bots.statistics.repository")
@ComponentScan("com.chelsenok.bots.statistics")
@EntityScan("com.chelsenok.bots.statistics.repository.entities")
@EnableScheduling
@EnableAsync
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
    open fun youtube() = YouTube()

    @Bean(name = ["fixedRate"])
    open fun fixedRate(): Long = 60000

    @Bean
    open fun logger(): Logger = LogManager.getLogger()

    @Bean
    @Autowired
    open fun graphQL(appSchema: AppSchema): GraphQL = GraphQL.newGraphQL(
            appSchema.build()).build()
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
