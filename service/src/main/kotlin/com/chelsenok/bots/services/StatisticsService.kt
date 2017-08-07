package com.chelsenok.bots.services

import com.chelsenok.bots.dtos.StatInfoGet
import com.chelsenok.bots.dtos.VideoPost

interface StatisticsService {
    fun getAllStatsInfoByVideoId(videoId: String): List<StatInfoGet>

    fun isVideoValid(v: String): Boolean

    fun addVideo(v: VideoPost)

}