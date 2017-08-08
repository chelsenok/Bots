package com.chelsenok.bots.schedulers

import com.chelsenok.bots.converters.ConverterFactory
import com.chelsenok.bots.entities.Report
import com.chelsenok.bots.entities.Video
import com.chelsenok.bots.repositories.ReportRepository
import com.chelsenok.bots.repositories.VideoRepository
import com.chelsenok.bots.youtube.YouTube
import com.chelsenok.bots.youtube.YouTubeReport
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
open class ScheduledTasks {

    @Autowired
    lateinit var reportRepository: ReportRepository

    @Autowired
    lateinit var videoRepository: VideoRepository

    @Scheduled(fixedRate = 60000)
    @PostConstruct
    open fun doReports() {
        val youtube = YouTube()
        videoRepository.findAll().forEach { it: Video -> writeReport(it, youtube) }
    }

    @Async
    private fun writeReport(video: Video, youtube: YouTube) {
        val report = ConverterFactory
                .get<YouTubeReport, Report>()!!.convertDtoToEntity(youtube.getReport(video.id)!!)
        reportRepository.saveAndFlush(report)
    }
}