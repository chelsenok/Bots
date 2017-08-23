package com.chelsenok.bots.statistics.service

import com.chelsenok.bots.statistics.service.dtos.StatInfoGet
import com.chelsenok.bots.statistics.service.dtos.VideoGet
import com.chelsenok.bots.statistics.service.dtos.VideoPost

interface StatisticsService {

    fun getAllStatsInfoByVideoId(videoId: String): List<StatInfoGet>

    fun isVideoValid(v: String): Boolean

    fun addVideo(v: VideoPost)

    fun isVideoExists(id: String): Boolean

    fun getIdsByFilter(videoId: String, likeCount: Long?, dislikeCount: Long?): List<Long>

    fun getExistByFilter(videoId: String, likeCount: Long?, commentCount: Long?): Boolean

    fun getAllStatsInfo(ids: ArrayList<String>?, from: Long?, offset: Long?,
                        to: Long?): List<StatInfoGet>

    fun getVideo(id: String): VideoGet?

    fun getFilteredStatsInfo(list: List<StatInfoGet>, from: Long?, offset: Long?,
                             to: Long?): List<StatInfoGet>

}