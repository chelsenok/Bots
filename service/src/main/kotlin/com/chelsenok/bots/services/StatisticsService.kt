package com.chelsenok.bots.services

import com.chelsenok.bots.dtos.StatInfo

interface StatisticsService {
    fun getAllStatsInfoByVideoId(videoId: String): List<StatInfo>
}