package com.chelsenok.bots.schedulers

import com.chelsenok.bots.YouTube
import com.chelsenok.bots.entities.Report
import com.chelsenok.bots.entities.Video
import com.chelsenok.bots.repositories.ReportRepository
import com.chelsenok.bots.repositories.VideoRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

@Component
open class ReportScheduler : ReportWritable {

    @Autowired
    private lateinit var reportRepository: ReportRepository

    @Autowired
    private lateinit var videoRepository: VideoRepository

    @Autowired
    private lateinit var modelMapper: ModelMapper

    @Scheduled(fixedRate = 60000)
    @PostConstruct
    override fun doReports() {
        val youtube = YouTube()
        videoRepository.findAll().forEach { it: Video -> writeReport(it, youtube) }
    }

    @Async
    override fun writeReport(video: Video, youtube: YouTube) {
        val report = modelMapper.map(youtube.getReport(video.id), Report::class.java)
//                ConverterFactory
//                .get<YouTubeReport, Report>()?.convert(youtube.getReport(video.id))
        report.time = Calendar.getInstance(TimeZone.getTimeZone("GMT")).timeInMillis
        reportRepository.saveAndFlush(report)
    }
}