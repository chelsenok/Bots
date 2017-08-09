package com.chelsenok.bots.converters

import com.chelsenok.bots.entities.Report
import com.chelsenok.bots.YouTubeReport
import org.springframework.core.convert.converter.Converter
import java.util.*

interface YouTubeReportToReportConverter : Converter<YouTubeReport, Report> {

    override fun convert(p0: YouTubeReport): Report {
        val report = Report()
        report.videoId = p0.videoId
        report.time = Calendar.getInstance(TimeZone.getTimeZone("GMT")).timeInMillis
        report.subscriberCount = p0.subscriberCount
        report.viewCount = p0.viewCount
        report.likeCount = p0.likeCount
        report.dislikeCount = p0.dislikeCount
        report.commentCount = p0.commentCount
        return report        
    }
}