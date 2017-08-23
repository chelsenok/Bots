package com.chelsenok.bots.statistics.web.objecttypes

import com.chelsenok.bots.statistics.web.Arguments
import com.chelsenok.bots.statistics.web.datafetchers.ReportsDataFetcher
import graphql.schema.GraphQLArgument
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLList
import graphql.schema.GraphQLObjectType
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class VideoObjectType : GraphQLObjectType.Builder(), InitializingBean {

    @Autowired
    @Qualifier("report")
    private lateinit var report: ReportObjectType

    @Autowired
    @Qualifier("fixedRate")
    private var fixedRate: Long = 0

    @Autowired
    private lateinit var reportsDataFetcher: ReportsDataFetcher

    override fun afterPropertiesSet() {
        this
                .name("Video")
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .name("reports")
                        .type(GraphQLList.list(report.build()))
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
                        .dataFetcher(reportsDataFetcher))
    }
}