package com.chelsenok.bots.services

import com.chelsenok.bots.YouTube
import com.chelsenok.bots.dtos.StatInfoGet
import com.chelsenok.bots.dtos.VideoPost
import com.chelsenok.bots.entities.Video
import com.chelsenok.bots.repositories.ReportRepository
import com.chelsenok.bots.repositories.VideoRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StatisticsServiceImpl : StatisticsService {
    @Autowired
    private lateinit var reportRepository: ReportRepository

    @Autowired
    private lateinit var videoRepository: VideoRepository

    @Autowired
    private lateinit var modelMapper: ModelMapper

    override fun isVideoExists(id: String) = videoRepository.exists(id)

    override fun getAllStatsInfoByVideoId(videoId: String): List<StatInfoGet> {
        return ArrayList<StatInfoGet>(reportRepository.findAllByVideoId(videoId).map { stat ->
            modelMapper.map(stat, StatInfoGet::class.java)
        })
    }

    override fun isVideoValid(v: String) = !isVideoExists(v) && YouTube().isVideoExists(v)

    override fun addVideo(v: VideoPost) {
        val video: Video? = modelMapper.map(v, Video::class.java)
        videoRepository.saveAndFlush(video)
    }

}
