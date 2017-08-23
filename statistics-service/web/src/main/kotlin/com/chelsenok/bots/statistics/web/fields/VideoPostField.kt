package com.chelsenok.bots.statistics.web.fields

import com.chelsenok.bots.statistics.web.Arguments
import com.chelsenok.bots.statistics.web.datafetchers.AddVideoDataFetcher
import graphql.Scalars
import graphql.schema.GraphQLArgument
import graphql.schema.GraphQLFieldDefinition
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class VideoPostField : GraphQLFieldDefinition.Builder(), InitializingBean {

    @Autowired
    private lateinit var addVideoDataFetcher: AddVideoDataFetcher

    override fun afterPropertiesSet() {
        this
                .name("addVideo")
                .type(Scalars.GraphQLString)
                .argument(GraphQLArgument.newArgument()
                        .name(Arguments.ID.string)
                        .type(Arguments.ID.type)
                )
                .dataFetcher(addVideoDataFetcher)
    }
}