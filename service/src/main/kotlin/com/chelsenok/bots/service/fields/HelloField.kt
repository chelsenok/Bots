package com.chelsenok.bots.service.fields

import graphql.Scalars
import graphql.schema.GraphQLFieldDefinition

class HelloField : GraphQLFieldDefinition.Builder() {
    init {
        this
                .name("hello")
                .type(Scalars.GraphQLString)
                .staticValue("world")
    }
}