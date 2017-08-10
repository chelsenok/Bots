package com.chelsenok.bots

import org.modelmapper.ModelMapper
import org.modelmapper.TypeMap
import org.modelmapper.convention.MatchingStrategies
import org.modelmapper.convention.NameTokenizers
import org.modelmapper.convention.NamingConventions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class Configuration {

    @Bean
    open fun modelMapping(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STRICT
        return modelMapper
    }

    @Bean
    open fun youtube() = YouTube()
}