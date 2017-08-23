package com.chelsenok.bots.statistics.web

import graphql.Scalars
import graphql.schema.GraphQLInputType
import graphql.schema.GraphQLList

enum class Arguments(val string: String, val type: GraphQLInputType) {
    FROM("from", Scalars.GraphQLLong),
    OFFSET("offset", Scalars.GraphQLLong),
    TO("to", Scalars.GraphQLLong),
    ID("id", Scalars.GraphQLString),
    IDS("ids", GraphQLList.list(Scalars.GraphQLString))
}