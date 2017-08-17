package com.chelsenok.bots.web.datafetchers

import com.chelsenok.bots.service.StatisticsService
import com.chelsenok.bots.service.dtos.VideoGet
import com.chelsenok.bots.web.Arguments
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class VideoDataFetcher : DataFetcher<VideoGet> {

    @Autowired
    private lateinit var statisticsService: StatisticsService

    override fun get(p0: DataFetchingEnvironment?): VideoGet? {
        val id = p0?.getArgument<String>(Arguments.ID.string)
        return if (id == null) {
            null
        } else {
            statisticsService.getVideo(id)
        }
    }
}