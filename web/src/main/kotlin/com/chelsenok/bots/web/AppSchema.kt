package com.chelsenok.bots.web

import com.chelsenok.bots.web.fields.StatsField
import com.chelsenok.bots.web.fields.VideoField
import com.chelsenok.bots.web.fields.VideoPostField
import graphql.schema.GraphQLObjectType
import graphql.schema.GraphQLSchema
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AppSchema : GraphQLSchema.Builder(), InitializingBean {

    @Autowired
    private lateinit var videoPostField: VideoPostField

    @Autowired
    private lateinit var statsField: StatsField

    @Autowired
    private lateinit var videoField: VideoField

    override fun afterPropertiesSet() {
        this
                .query(
                        GraphQLObjectType.newObject()
                                .name("query")
                                .field(statsField)
                                .field(videoField)
                                .build()
                )
                .mutation(
                        GraphQLObjectType.newObject()
                                .name("mutation")
                                .field(videoPostField)
                                .build()
                )
    }
}