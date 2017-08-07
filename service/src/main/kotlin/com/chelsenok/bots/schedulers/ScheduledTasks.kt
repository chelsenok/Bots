package com.chelsenok.bots.schedulers

import com.chelsenok.bots.converters.ConverterFactory
import com.chelsenok.bots.entities.Report
import com.chelsenok.bots.repositories.StatRepository
import com.chelsenok.bots.repositories.VideoRepository
import com.chelsenok.bots.youtube.YouTube
import com.chelsenok.bots.youtube.YouTubeReport
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


@Component
open class ScheduledTasks {

    @Autowired
    lateinit var statRepository: StatRepository

    @Autowired
    lateinit var videoRepository: VideoRepository

    @Scheduled(fixedRate = 60000)
    fun doReports() {
        val youtube = YouTube()
        videoRepository.findAll()
                .map { youtube.getReport(it.id) }
                .forEach {
                    Thread(Runnable {
                        statRepository.saveAndFlush(ConverterFactory
                                .get<YouTubeReport, Report>()!!.convertDtoToEntity(it!!))
                    }).start()
                }
    }
}