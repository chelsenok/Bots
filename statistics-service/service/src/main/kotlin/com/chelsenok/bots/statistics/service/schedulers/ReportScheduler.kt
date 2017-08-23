package com.chelsenok.bots.statistics.service.schedulers

import com.chelsenok.bots.statistics.repository.ReportRepository
import com.chelsenok.bots.statistics.repository.VideoRepository
import com.chelsenok.bots.statistics.repository.entities.Report
import com.chelsenok.bots.statistics.repository.entities.Video
import com.chelsenok.youtube.YouTube
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

    @Autowired
    private lateinit var youtube: YouTube

    @Scheduled(fixedRate = 60000)
    @PostConstruct
    override fun doReports() {
        videoRepository.findAll().forEach { it: Video -> writeReport(it, youtube) }
    }

    @Async
    override fun writeReport(video: Video, youtube: YouTube) {
        val report = modelMapper.map(youtube.getReport(video.id), Report::class.java)
        report.time = Calendar.getInstance(TimeZone.getTimeZone("GMT")).timeInMillis
        reportRepository.saveAndFlush(report)
    }
}