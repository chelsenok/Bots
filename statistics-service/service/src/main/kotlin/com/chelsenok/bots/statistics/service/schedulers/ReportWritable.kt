package com.chelsenok.bots.statistics.service.schedulers

import com.chelsenok.bots.statistics.repository.entities.Video
import com.chelsenok.youtube.YouTube

internal interface ReportWritable {

    fun doReports()

    fun writeReport(video: Video, youtube: YouTube)
}