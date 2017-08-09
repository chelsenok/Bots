package com.chelsenok.bots.services

import com.chelsenok.bots.YouTube
import com.chelsenok.bots.converters.ConverterFactory
import com.chelsenok.bots.dtos.StatInfoGet
import com.chelsenok.bots.dtos.VideoPost
import com.chelsenok.bots.entities.Report
import com.chelsenok.bots.entities.Video
import com.chelsenok.bots.repositories.ReportRepository
import com.chelsenok.bots.repositories.VideoRepository
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StatisticsServiceImpl : StatisticsService {
    @Autowired
    private lateinit var reportRepository: ReportRepository

    @Autowired
    private lateinit var videoRepository: VideoRepository

    private val LOGGER = LogManager.getLogger(this::class.java)

    override fun isVideoExists(id: String) = videoRepository.exists(id)

    override fun getAllStatsInfoByVideoId(videoId: String): List<StatInfoGet> {
        LOGGER.trace("stats is going for response")
        LOGGER.debug("stats is going for response")
        LOGGER.info("stats is going for response")
        LOGGER.warn("stats is going for response")
        LOGGER.error("stats is going for response")
        LOGGER.fatal("stats is going for response")
        return ArrayList<StatInfoGet>(reportRepository.findAllByVideoId(videoId).map { stat ->
            ConverterFactory.get<Report, StatInfoGet>()?.convert(stat)
        })
    }

    override fun isVideoValid(v: String) = !isVideoExists(v) && YouTube().isVideoExists(v)

    override fun addVideo(v: VideoPost) {
        val video: Video? = ConverterFactory
                .get<VideoPost, Video>()?.convert(v)
        videoRepository.saveAndFlush(video)
    }

}
