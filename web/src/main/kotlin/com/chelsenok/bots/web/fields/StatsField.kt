package com.chelsenok.bots.web.fields

import com.chelsenok.bots.web.datafetchers.StatsDataFetcher
import graphql.Scalars
import graphql.schema.GraphQLArgument
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLFieldDefinition.newFieldDefinition
import graphql.schema.GraphQLList
import graphql.schema.GraphQLObjectType.newObject
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class StatsField : GraphQLFieldDefinition.Builder(), InitializingBean {

    @Autowired
    lateinit var statsDataFetcher: StatsDataFetcher

    override fun afterPropertiesSet() {
        val statType = newObject()
                .name("stat")
                .field(newFieldDefinition()
                        .name("time")
                        .type(Scalars.GraphQLLong))
                .field(newFieldDefinition()
                        .name("subscriberCount")
                        .type(Scalars.GraphQLLong))
                .field(newFieldDefinition()
                        .name("viewCount")
                        .type(Scalars.GraphQLLong))
                .field(newFieldDefinition()
                        .name("likeCount")
                        .type(Scalars.GraphQLLong))
                .field(newFieldDefinition()
                        .name("dislikeCount")
                        .type(Scalars.GraphQLLong))
                .field(newFieldDefinition()
                        .name("commentCount")
                        .type(Scalars.GraphQLLong))
                .build()

        this
                .name("stats")
                .type(GraphQLList.list(statType))
                .argument(GraphQLArgument.newArgument()
                        .name("id")
                        .type(Scalars.GraphQLString)
                )
                .dataFetcher(statsDataFetcher)
    }

}