package com.chelsenok.bots.web.objecttypes

import graphql.Scalars
import graphql.schema.GraphQLFieldDefinition
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("stat")
class StatObjectType : ReportObjectType() {

    override fun afterPropertiesSet() {
        super.afterPropertiesSet()
        this
                .name("Stat")
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .name("videoId")
                        .type(Scalars.GraphQLString))
    }
}
