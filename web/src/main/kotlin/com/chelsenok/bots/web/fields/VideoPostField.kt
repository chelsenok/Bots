package com.chelsenok.bots.web.fields

import com.chelsenok.bots.web.datafetchers.AddVideoDataFetcher
import graphql.Scalars
import graphql.schema.GraphQLArgument
import graphql.schema.GraphQLFieldDefinition
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class VideoPostField : GraphQLFieldDefinition.Builder(), InitializingBean {

    @Autowired
    lateinit var addVideoDataFetcher: AddVideoDataFetcher

    override fun afterPropertiesSet() {
        this
                .name("addVideo")
                .type(Scalars.GraphQLString)
                .argument(GraphQLArgument.newArgument()
                        .name("id")
                        .type(Scalars.GraphQLString)
                )
                .dataFetcher(addVideoDataFetcher)
    }

}