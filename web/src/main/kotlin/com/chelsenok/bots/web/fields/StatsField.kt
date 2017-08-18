package com.chelsenok.bots.web.fields

import com.chelsenok.bots.web.Arguments
import com.chelsenok.bots.web.datafetchers.StatsDataFetcher
import com.chelsenok.bots.web.objecttypes.StatObjectType
import graphql.schema.GraphQLArgument
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLList
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component


@Component
class StatsField : GraphQLFieldDefinition.Builder(), InitializingBean {

    @Autowired
    private lateinit var statsDataFetcher: StatsDataFetcher

    @Autowired
    private lateinit var stat: StatObjectType

    @Autowired
    @Qualifier("fixedRate")
    private var fixedRate: Long = 0

    override fun afterPropertiesSet() {
        this
                .name("stats")
                .argument(GraphQLArgument.newArgument()
                        .name(Arguments.FROM.string)
                        .type(Arguments.FROM.type)
                )
                .argument(GraphQLArgument.newArgument()
                        .name(Arguments.OFFSET.string)
                        .type(Arguments.OFFSET.type)
                        .defaultValue(fixedRate)
                )
                .argument(GraphQLArgument.newArgument()
                        .name(Arguments.TO.string)
                        .type(Arguments.TO.type)
                )
                .argument(GraphQLArgument.newArgument()
                        .name(Arguments.IDS.string)
                        .type(Arguments.IDS.type)
                )
                .type(GraphQLList.list(stat.build()))
                .dataFetcher(statsDataFetcher)
    }

}