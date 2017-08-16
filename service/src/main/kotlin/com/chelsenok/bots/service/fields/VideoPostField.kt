package com.chelsenok.bots.service.fields

import com.chelsenok.bots.service.datafetchers.AddVideoDataFetcher
import graphql.Scalars
import graphql.schema.GraphQLArgument
import graphql.schema.GraphQLFieldDefinition

class VideoPostField : GraphQLFieldDefinition.Builder() {
    init {
        this
                .name("addVideo")
                .type(Scalars.GraphQLString)
                .argument(GraphQLArgument.newArgument()
                        .name("id")
                        .type(Scalars.GraphQLString)
                )
                .dataFetcher(AddVideoDataFetcher())
    }
}