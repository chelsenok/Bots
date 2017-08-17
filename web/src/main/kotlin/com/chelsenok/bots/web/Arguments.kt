package com.chelsenok.bots.web

import graphql.Scalars
import graphql.schema.GraphQLInputType

enum class Arguments(val string: String, val type: GraphQLInputType) {
    FROM("from", Scalars.GraphQLLong),
    OFFSET("offset", Scalars.GraphQLLong),
    TO("to", Scalars.GraphQLLong),
    ID("id", Scalars.GraphQLString)
}