package com.chelsenok.bots.service

import com.chelsenok.bots.service.dtos.StatInfoGet
import com.chelsenok.bots.service.dtos.VideoPost

interface StatisticsService {

    fun getAllStatsInfoByVideoId(videoId: String): List<StatInfoGet>

    fun isVideoValid(v: String): Boolean

    fun addVideo(v: VideoPost)

    fun isVideoExists(id: String): Boolean

    fun getIdByFilter(videoId: String?, likeCount: Long?, dislikeCount: Long?): List<Long>

}