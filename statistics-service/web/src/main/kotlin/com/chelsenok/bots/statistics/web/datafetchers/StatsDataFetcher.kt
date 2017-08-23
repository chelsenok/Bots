package com.chelsenok.bots.statistics.web.datafetchers

import com.chelsenok.bots.statistics.service.StatisticsService
import com.chelsenok.bots.statistics.service.dtos.StatInfoGet
import com.chelsenok.bots.statistics.web.Arguments
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class StatsDataFetcher : DataFetcher<List<StatInfoGet>> {

    @Autowired
    private lateinit var statisticsService: StatisticsService

    override fun get(p0: DataFetchingEnvironment?): List<StatInfoGet>? =
            statisticsService.getAllStatsInfo(
                    p0?.getArgument<ArrayList<String>>(Arguments.IDS.string),
                    p0?.getArgument<Long>(Arguments.FROM.string),
                    p0?.getArgument<Long>(Arguments.OFFSET.string),
                    p0?.getArgument<Long>(Arguments.TO.string)
            )

}