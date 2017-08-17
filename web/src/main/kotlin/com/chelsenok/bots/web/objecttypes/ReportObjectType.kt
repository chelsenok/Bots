package com.chelsenok.bots.web.objecttypes

import graphql.Scalars
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLObjectType
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("report")
open class ReportObjectType : GraphQLObjectType.Builder(), InitializingBean {

    override fun afterPropertiesSet() {
        this
                .name("Report")
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .name("time")
                        .type(Scalars.GraphQLLong))
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .name("subscriberCount")
                        .type(Scalars.GraphQLLong))
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .name("viewCount")
                        .type(Scalars.GraphQLLong))
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .name("likeCount")
                        .type(Scalars.GraphQLLong))
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .name("dislikeCount")
                        .type(Scalars.GraphQLLong))
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .name("commentCount")
                        .type(Scalars.GraphQLLong))
    }
}
