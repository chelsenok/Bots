package com.chelsenok.bots.web

import com.chelsenok.bots.service.fields.HelloField
import com.chelsenok.bots.service.fields.VideoPostField
import graphql.schema.GraphQLObjectType
import graphql.schema.GraphQLSchema

class AppSchema : GraphQLSchema.Builder() {
    init {
        this
                .query(
                        GraphQLObjectType.newObject()
                                .name("query")
                                .field(HelloField())
                                .build()
                )
                .mutation(
                        GraphQLObjectType.newObject()
                                .name("mutation")
                                .field(VideoPostField())
                                .build()
                )
    }
}