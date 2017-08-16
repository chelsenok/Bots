package com.chelsenok.bots.web.datafetchers

import com.chelsenok.bots.service.StatisticsService
import com.chelsenok.bots.service.dtos.VideoPost
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AddVideoDataFetcher : DataFetcher<String> {

    @Autowired
    private lateinit var statisticsService: StatisticsService

    override fun get(p0: DataFetchingEnvironment?): String? {
        val id = p0!!.getArgument<String>("id")
        return if (statisticsService.isVideoValid(id)) {
            statisticsService.addVideo(VideoPost(id))
            id
        } else {
            null
        }
    }
}