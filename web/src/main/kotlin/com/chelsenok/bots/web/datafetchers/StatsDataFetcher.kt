package com.chelsenok.bots.web.datafetchers

import com.chelsenok.bots.service.StatisticsService
import com.chelsenok.bots.service.dtos.StatInfoGet
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class StatsDataFetcher : DataFetcher<List<StatInfoGet>> {

    @Autowired
    private lateinit var statisticsService: StatisticsService

    override fun get(p0: DataFetchingEnvironment?): List<StatInfoGet>? {
        val id = p0!!.getArgument<String>("id")
        return if (statisticsService.isVideoExists(id)) {
            statisticsService.getAllStatsInfoByVideoId(id)
        } else {
            null
        }
    }
}