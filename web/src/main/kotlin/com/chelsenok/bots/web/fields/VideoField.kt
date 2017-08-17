package com.chelsenok.bots.web.fields

import com.chelsenok.bots.web.Arguments
import com.chelsenok.bots.web.datafetchers.VideoDataFetcher
import com.chelsenok.bots.web.objecttypes.VideoObjectType
import graphql.schema.GraphQLArgument
import graphql.schema.GraphQLFieldDefinition
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class VideoField : GraphQLFieldDefinition.Builder(), InitializingBean {

    @Autowired
    private lateinit var video: VideoObjectType

    @Autowired
    private lateinit var videoDataFetcher: VideoDataFetcher

    override fun afterPropertiesSet() {

        this
                .name("video")
                .type(video)
                .argument(GraphQLArgument.newArgument()
                        .name(Arguments.ID.string)
                        .type(Arguments.ID.type))
                .dataFetcher(videoDataFetcher)
    }

}