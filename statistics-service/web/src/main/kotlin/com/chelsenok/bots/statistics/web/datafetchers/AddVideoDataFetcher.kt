package com.chelsenok.bots.statistics.web.datafetchers

import com.chelsenok.bots.statistics.service.StatisticsService
import com.chelsenok.bots.statistics.service.dtos.VideoPost
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AddVideoDataFetcher : DataFetcher<VideoPost> {

    @Autowired
    private lateinit var statisticsService: StatisticsService

    override fun get(p0: DataFetchingEnvironment?): VideoPost? {
        val id = p0?.getArgument<String>("id")
        return if (id == null || !statisticsService.isVideoValid(id)) {
            null
        } else {
            val video = VideoPost(id)
            statisticsService.addVideo(video)
            video
        }
    }
}