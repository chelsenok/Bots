package com.chelsenok.bots.schedulers

import com.chelsenok.bots.YouTube
import com.chelsenok.bots.YouTubeReport
import com.chelsenok.bots.converters.ConverterFactory
import com.chelsenok.bots.entities.Report
import com.chelsenok.bots.entities.Video
import com.chelsenok.bots.repositories.ReportRepository
import com.chelsenok.bots.repositories.VideoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
open class ReportScheduler: ReportWritable {

    @Autowired
    private lateinit var reportRepository: ReportRepository

    @Autowired
    private lateinit var videoRepository: VideoRepository

    @Scheduled(fixedRate = 60000)
    @PostConstruct
    override fun doReports() {
        val youtube = YouTube()
        videoRepository.findAll().forEach { it: Video -> writeReport(it, youtube) }
    }

    @Async
    override fun writeReport(video: Video, youtube: YouTube) {
        val report = ConverterFactory
                .get(YouTubeReport::class.java, Report::class.java)!!
                .convert(youtube.getReport(video.id)!!)
        reportRepository.saveAndFlush(report)
    }
}