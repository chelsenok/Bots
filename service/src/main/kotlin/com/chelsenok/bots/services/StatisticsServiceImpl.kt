package com.chelsenok.bots.services

import com.chelsenok.bots.converters.ConverterFactory
import com.chelsenok.bots.dtos.StatInfoGet
import com.chelsenok.bots.dtos.VideoPost
import com.chelsenok.bots.entities.Video
import com.chelsenok.bots.repositories.ReportRepository
import com.chelsenok.bots.repositories.VideoRepository
import com.chelsenok.bots.youtube.YouTube
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StatisticsServiceImpl : StatisticsService {
    @Autowired
    lateinit var reportRepository: ReportRepository

    @Autowired
    lateinit var videoRepository: VideoRepository

    override fun getAllStatsInfoByVideoId(videoId: String): List<StatInfoGet> {
        return ArrayList<StatInfoGet>(reportRepository.findAllByVideoId(videoId).map { stat ->
            StatInfoGet(stat.id, stat.time)
        })
    }

    override fun isVideoValid(v: String): Boolean =
            !videoRepository.exists(v)
                    && YouTube().isVideoExists(v)

    override fun addVideo(v: VideoPost) {
        val video: Video? = ConverterFactory.get<VideoPost, Video>()?.convertDtoToEntity(v)
        videoRepository.saveAndFlush(video)
    }

}
