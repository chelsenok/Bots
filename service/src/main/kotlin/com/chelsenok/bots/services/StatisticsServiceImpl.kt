package com.chelsenok.bots.services

import com.chelsenok.bots.dtos.StatInfo
import com.chelsenok.bots.repositories.StatRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class StatisticsServiceImpl : StatisticsService {

    @Autowired
    lateinit var statRepository: StatRepository


    override fun getAllStatsInfoByVideoId(videoId: String): List<StatInfo> {
        return ArrayList<StatInfo>(statRepository.findAllByVideoId(videoId).map { stat ->
            StatInfo(stat.id, stat.time)
        })
    }
}
