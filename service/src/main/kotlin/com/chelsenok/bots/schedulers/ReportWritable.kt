package com.chelsenok.bots.schedulers

import com.chelsenok.bots.YouTube
import com.chelsenok.bots.entities.Video

internal interface ReportWritable {

    fun doReports()

    fun writeReport(video: Video, youtube: YouTube)
}