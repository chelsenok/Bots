package com.chelsenok.bots.web.datafetchers

import com.chelsenok.bots.service.StatisticsService
import com.chelsenok.bots.service.dtos.StatInfoGet
import com.chelsenok.bots.web.Arguments
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
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