package com.chelsenok.bots.service

import com.chelsenok.bots.repository.entities.Video
import com.chelsenok.bots.repository.ReportRepository
import com.chelsenok.bots.repository.VideoRepository
import com.chelsenok.bots.service.dtos.StatInfoGet
import com.chelsenok.bots.service.dtos.VideoPost
import com.chelsenok.youtube.YouTube
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

    @Autowired
    private lateinit var youtube: YouTube

    override fun isVideoExists(id: String) = videoRepository.exists(id)

    override fun getAllStatsInfoByVideoId(videoId: String): List<StatInfoGet> {
        return ArrayList<StatInfoGet>(reportRepository.findAllByVideoId(videoId).map { stat ->
            modelMapper.map(stat, StatInfoGet::class.java)
        })
    }

    override fun isVideoValid(v: String) = !isVideoExists(v) && youtube.isVideoExists(v)

    override fun addVideo(v: VideoPost) {
        val video: Video? = modelMapper.map(v, Video::class.java)
        videoRepository.saveAndFlush(video)
    }

}
