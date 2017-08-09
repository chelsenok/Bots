package com.chelsenok.bots.services

import com.chelsenok.bots.YouTube
import com.chelsenok.bots.converters.ConverterFactory
import com.chelsenok.bots.dtos.StatInfoGet
import com.chelsenok.bots.dtos.VideoPost
import com.chelsenok.bots.entities.Report
import com.chelsenok.bots.entities.Video
import com.chelsenok.bots.repositories.ReportRepository
import com.chelsenok.bots.repositories.VideoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StatisticsServiceImpl : StatisticsService {
    @Autowired
    lateinit var reportRepository: ReportRepository

    @Autowired
    lateinit var videoRepository: VideoRepository

    override fun isVideoExists(id: String) = videoRepository.exists(id)

    override fun getAllStatsInfoByVideoId(videoId: String): List<StatInfoGet> {
        return ArrayList<StatInfoGet>(reportRepository.findAllByVideoId(videoId).map { stat ->
            ConverterFactory.get(Report::class.java, StatInfoGet::class.java)?.convert(stat)
        })
    }

    override fun isVideoValid(v: String) = !isVideoExists(v) && YouTube().isVideoExists(v)

    override fun addVideo(v: VideoPost) {
        val video: Video? = ConverterFactory
                .get(VideoPost::class.java, Video::class.java)?.convert(v)
        videoRepository.saveAndFlush(video)
    }

}
