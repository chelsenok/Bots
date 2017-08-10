package com.chelsenok.bots.service.schedulers

import com.chelsenok.bots.repository.entities.Video
import com.chelsenok.youtube.YouTube

internal interface ReportWritable {

    fun doReports()

    fun writeReport(video: Video, youtube: YouTube)
}